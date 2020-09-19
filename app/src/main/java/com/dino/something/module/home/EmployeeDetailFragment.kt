package com.dino.something.module.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dino.something.R
import com.dino.something.databinding.FragmentEmployeeDetailBinding
import com.dino.something.utility.ImageHandler
import com.google.gson.Gson

class EmployeeDetailFragment : Fragment() {
    private lateinit var employee: Employee
    private lateinit var binding: FragmentEmployeeDetailBinding
    private lateinit var viewModel: EmployeeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_detail, container, false)
        viewModel = ViewModelProvider(this).get(EmployeeDetailViewModel::class.java)
        binding.viewModel = viewModel

        setObservers()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (arguments?.get("mData")!=null) {
            val jsonString = arguments?.get("mData") as String?
            employee = Gson().fromJson(jsonString, Employee::class.java)
            binding.employee = employee
            ImageHandler.setGlideRoundImage(employee.profile_image, binding.profileImage)
        }
    }
    private fun setObservers() {

        viewModel.eventBack.observe(viewLifecycleOwner, { isClicked ->
            if (isClicked) {
                findNavController().popBackStack()
                viewModel.onBackClickComplete()
            }
        })
    }
}