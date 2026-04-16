package com.example.myapplication.ui.task


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTaskBinding
import com.example.myapplication.data.task.Task

class TaskAdapter(private var taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.tvTaskTitle.text = task.title
        holder.binding.tvTaskDescription.text = task.description
        holder.binding.tvTaskReminder.text =
            if (task.hasReminder) "Con recordatorio" else "Sin recordatorio"
    }

    override fun getItemCount(): Int = taskList.size

    fun updateList(newList: List<Task>) {
        taskList = newList
        notifyDataSetChanged()
    }
}