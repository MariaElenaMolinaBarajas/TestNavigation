package com.example.testnavigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.testnavigation.data.User
import com.example.testnavigation.data.UserDataBase
import com.example.testnavigation.data.UserRepository
import com.example.testnavigation.databinding.FragmentHomeBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

/**
 * Fragment Home : Fragment()
 */
private const val packageName = "com.example.fragmentsnavigation"
private const val nativeSampleClassname = "$packageName.CamaraActivity"

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory
    private val moduleCamara by lazy {getString(R.string.module_camara) }
    private lateinit var manager: SplitInstallManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val dao = UserDataBase.getDataBase(requireActivity().application).userDao
        val repository = UserRepository(dao)
        viewModelFactory = HomeViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = this

        manager = SplitInstallManagerFactory.create(this.requireContext())

        binding.btnNavigation.setOnClickListener{
            loadAndLaunchModule(moduleCamara)
        }

        viewModel.getUser.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                binding.etName.setText("")
            } else {
                binding.etName.setText(it.name)
            }
        })

        binding.btnWelcome.setOnClickListener {

            viewModel.getUser.observe(viewLifecycleOwner, Observer {
                if (it == null){
                    Toast.makeText(requireContext(), "vacío", Toast.LENGTH_SHORT).show()
                    viewModel.save(User(1, binding.etName.text.toString()))
                }else{
                    Toast.makeText(requireContext(), "no vacío", Toast.LENGTH_SHORT).show()
                    viewModel.update(User(1, binding.etName.text.toString()))
                }
            })
        }

        return  binding.root
    }

    /*private fun insertDataToDataBase() {
       val name = binding.etName.text.toString()
        val user = User(0, name)
        viewModel.addUser(user )
    }*/


    /**
     * Load a feature by module name.
     * @param name The name of the feature module to load.
     */
    private fun loadAndLaunchModule(name: String) {

        if (manager.installedModules.contains(name)) {
            onSuccessfulLoad(name, launch = true)
            return
        }
        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        // Load and install the requested feature module.
        manager.startInstall(request)

    }

    /**
     * Define what to do once a feature module is loaded successfully.
     * @param moduleName The name of the successfully loaded module.
     * @param launch `true` if the feature module should be launched, else `false`.
     */
    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                moduleCamara ->  launchActivity(nativeSampleClassname)
            }
        }
    }

    /** Launch an activity by its class name. */
    private fun launchActivity(className: String) {
        Intent().setClassName(requireContext(), className)
            .also {
                startActivity(it)
            }
    }
}