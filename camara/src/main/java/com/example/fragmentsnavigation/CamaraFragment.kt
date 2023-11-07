package com.example.fragmentsnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.fragmentsnavigation.databinding.FragmentCamaraBinding

/**
 * Camera Fragment : Fragment()
 * */
class CamaraFragment : Fragment() {
    private lateinit var binding: FragmentCamaraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camara, container, false)
        binding.btnTakePhoto.setOnClickListener {
            findNavController().navigate(R.id.action_camaraFragment_to_welcomeFragment)
        }
        return  binding.root
    }
}