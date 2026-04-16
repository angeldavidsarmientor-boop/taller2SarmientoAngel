package com.example.myapplication.data.task

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskRepository(context: Context) {

    companion object {
        private const val PREFS_NAME = "tasks_prefs"
        private const val KEY_TASK_LIST = "task_list"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()

    // Lista en memoria
    private var tasksInMemory: MutableList<Task> = loadTasksFromPrefs()

    /** Devuelve todas las tareas */
    fun getAllTasks(): List<Task> = tasksInMemory.toList()

    /** Agrega una tarea y la guarda */
    fun addTask(task: Task) {
        tasksInMemory.add(task)
        saveTasksToPrefs()
    }

    /** Actualiza una tarea existente */
    fun updateTask(updated: Task) {
        val index = tasksInMemory.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            tasksInMemory[index] = updated
            saveTasksToPrefs()
        }
    }

    /** Elimina una tarea por su ID */
    fun deleteTask(taskId: Int) {
        tasksInMemory.removeAll { it.id == taskId }
        saveTasksToPrefs()
    }

    // ==========================
    // Persistencia con JSON
    // ==========================

    /** Carga las tareas desde SharedPreferences */
    private fun loadTasksFromPrefs(): MutableList<Task> {
        val json = prefs.getString(KEY_TASK_LIST, null)
            ?: return mutableListOf()

        val type = object : TypeToken<List<Task>>() {}.type

        return try {
            val list: List<Task> = gson.fromJson(json, type)
            list.toMutableList()
        } catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    /** Guarda las tareas en SharedPreferences */
    private fun saveTasksToPrefs() {
        val editor = prefs.edit()
        val json = gson.toJson(tasksInMemory)
        editor.putString(KEY_TASK_LIST, json)
        editor.apply()
    }
}