package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fragments.databinding.FragmentFrag1Binding
import java.text.SimpleDateFormat
import java.util.*

class Frag1 : Fragment() {
    private var _binding : FragmentFrag1Binding?=null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFrag1Binding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.timer.observe(viewLifecycleOwner,{ time->
            val sdf = SimpleDateFormat("ss")
            binding.counter.text = sdf.format(time)
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}