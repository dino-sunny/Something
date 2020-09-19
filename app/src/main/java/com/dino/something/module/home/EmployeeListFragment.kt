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
import com.dino.something.databinding.FragmentEmployeeListBinding
import com.google.gson.Gson

class EmployeeListFragment : Fragment() {

    private lateinit var viewModel: EmployeeListViewModel
    private lateinit var binding: FragmentEmployeeListBinding
    private lateinit var employeeListAdapter: EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false)
        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
        binding.viewModel = viewModel

        setAdapter()
        setObservers()
        return binding.root
    }

    //Observing live data
    private fun setObservers() {
        viewModel.response.observe(viewLifecycleOwner, {
            it?.let { list ->
                if (list.isNotEmpty()) {
                    employeeListAdapter.submitList(list)
                }
            }
        })
        viewModel.eventNavigateEmployee.observe(viewLifecycleOwner, { employee ->
            employee?.let {
                val mData = Gson().toJson(it)
                findNavController().navigate(
                    EmployeeListFragmentDirections
                        .actionEmployeeListFragmentToEmployeeDetailFragment(mData)
                )
                viewModel.onEmployeeNavigated()
            }
        })
    }

    private fun setAdapter() {
        binding.employeeList.setHasFixedSize(true)
        employeeListAdapter = EmployeeAdapter(
            EmployeeListListener { employee ->
                viewModel.onEmployeeClicked(employee)
            })
        binding.employeeList.adapter = employeeListAdapter
    }
}