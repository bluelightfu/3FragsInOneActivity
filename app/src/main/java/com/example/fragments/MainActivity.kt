package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fragments.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    private var startCounter = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment,Frag1())
            .add(R.id.fragment2,Frag2())
            .add(R.id.fragment3,Frag3())
            .commit()
        viewInit()
    }

    private fun viewInit() {
        binding.start.setOnClickListener {
            when(startCounter){
                true->{
                    GlobalScope.launch {
                        viewModel.start()
                    }
                    startCounter = false
                    binding.start.text = "Stop"
                }
                false->{
                    GlobalScope.launch {
                        viewModel.stop()
                    }
                    startCounter = true
                    binding.start.text = "Start"
                }
            }
        }
    }
}