package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.task.Task
import com.example.myapplication.data.task.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository(application.applicationContext)

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _tasks.value = repository.getAllTasks()
    }

    fun addTask(task: Task) {
        repository.addTask(task)
        loadTasks()
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
        loadTasks()
    }

    fun deleteTask(taskId: Int) {
        repository.deleteTask(taskId)
        loadTasks()
    }
}