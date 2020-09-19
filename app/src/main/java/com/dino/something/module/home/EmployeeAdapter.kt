package com.dino.something.module.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dino.something.databinding.EmployeeItemBinding
import com.dino.something.utility.ImageHandler

class EmployeeAdapter(private val clickListener: EmployeeListListener) : ListAdapter<Employee, ViewHolder>(
    RecentDiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EmployeeItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind((item),clickListener)
        ImageHandler.setGlideRoundImage(item.profile_image, holder.binding.profileImage)
    }
}

class ViewHolder(val binding: EmployeeItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item: Employee, clickListener: EmployeeListListener) {
        binding.employee = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class EmployeeListListener(val clickListener: (employee: Employee) -> Unit) {
    fun onClick(employee: Employee) = clickListener(employee)
}

class RecentDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}