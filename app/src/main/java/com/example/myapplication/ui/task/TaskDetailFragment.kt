package com.example.myapplication.ui.task

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.task.Task
import com.example.myapplication.databinding.FragmentTaskDetailBinding
import com.example.myapplication.receiver.TaskReminderReceiver
import com.example.myapplication.viewmodel.TaskViewModel

class TaskDetailFragment : Fragment() {

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveTask.setOnClickListener {
            val titulo = binding.etTaskTitle.text.toString().trim()
            val descripcion = binding.etTaskDescription.text.toString().trim()
            val recordatorio = binding.cbReminder.isChecked

            if (titulo.isEmpty()) {
                binding.etTaskTitle.error = "El título es obligatorio"
                return@setOnClickListener
            }

            val nuevaTarea = Task(
                id = System.currentTimeMillis().toInt(),
                title = titulo,
                description = descripcion,
                hasReminder = recordatorio
            )

            // Guardar en ViewModel
            taskViewModel.addTask(nuevaTarea)

            // 🔔 Programar recordatorio si está activado
            if (recordatorio) {
                programarRecordatorio(titulo, descripcion)
            }

            Toast.makeText(
                requireContext(),
                "Tarea guardada correctamente",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigateUp()
        }

        binding.cbReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    requireContext(),
                    "Recordatorio activado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun programarRecordatorio(titulo: String, descripcion: String) {

        val intent = Intent(requireContext(), TaskReminderReceiver::class.java).apply {
            putExtra("TASK_TITLE", titulo)
            putExtra("TASK_DESCRIPTION", descripcion)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager =
            ContextCompat.getSystemService(requireContext(), AlarmManager::class.java)

        val triggerTime = System.currentTimeMillis() + 15_000

        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}