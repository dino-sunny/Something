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

class EmployeeListFragment : Fragment(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var viewModel: EmployeeListViewModel
    private lateinit var binding: FragmentEmployeeListBinding
    private lateinit var employeeListAdapter: EmployeeAdapter
    private lateinit var list: List<Employee>
    var searchList: MutableList<Employee> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false)
        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
        binding.viewModel = viewModel

        binding.search.setOnQueryTextListener(this)

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
                    this.list = list
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(searchKey: String?): Boolean {
        if (searchKey == "") {
            employeeListAdapter.submitList(list)
        } else {
            employeeListAdapter.submitList(getSearchList(searchKey))
        }
        return true
    }

    private fun getSearchList(searchKey: String?): MutableList<Employee>? {
        val event = list.find { it.name!!.contains(searchKey.toString()) || it.email!!.contains(searchKey.toString()) }
        searchList.clear()
        if (event != null) {
            searchList.add(event)
        }
        return searchList
    }
}