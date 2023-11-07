package com.example.fragmentsnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fragmentsnavigation.databinding.ActivityCamaraBinding

/**
 * Camara Activity : AppCompatActivity()
 */
class CamaraActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCamaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camara)
    }
}