package com.example.testnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.testnavigation.databinding.ActivityMainBinding

/**
 * Main Activity : AppCompatActivity()
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Toast.makeText(this, "Activity", Toast.LENGTH_SHORT).show()
    }
}