package com.example.taskmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllTaskFragment : Fragment() {
    private lateinit var taskList: MutableList<String>
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_task, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fabAddTask = view.findViewById<FloatingActionButton>(R.id.fabAddTask)

        // Initialize Task List
        taskList = mutableListOf() // Initially empty

        // Initialize Task Adapter with callback
        taskAdapter = TaskAdapter(taskList) { position ->
            // Remove task when clicked on "Yes" in the confirmation dialog
            taskAdapter.removeTask(position)
        }

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = taskAdapter

        // Set FAB Click Listener
        fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }

        return view
    }

    private fun showAddTaskDialog() {
        // Inflate the dialog layout
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_task, null)
        val etTaskName = dialogView.findViewById<EditText>(R.id.etTaskName)
        val spinnerCategory = dialogView.findViewById<Spinner>(R.id.spinnerCategory)

        // Set up Spinner for categories
        val categories = listOf("Work", "Personal", "Shopping", "Other")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = spinnerAdapter

        // Show AlertDialog to add a new task
        AlertDialog.Builder(requireContext())
            .setTitle("Add New Task")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val taskName = etTaskName.text.toString()
                val category = spinnerCategory.selectedItem.toString()

                if (taskName.isNotEmpty()) {
                    // Add task to list and notify adapter
                    taskList.add("$taskName ($category)")
                    taskAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Task name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}


class TaskAdapter(
    private val tasks: MutableList<String>,  // Change to MutableList to modify
    private val onTaskDeleted: (position: Int) -> Unit  // Callback to delete the task
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // ViewHolder to hold the views
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName: TextView = view.findViewById(R.id.taskName)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)  // Define the delete button
    }

    // Create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    // Bind the task data to the ViewHolder
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskName.text = tasks[position]

        // Set the delete button listener
        holder.btnDelete.setOnClickListener {
            // Show the confirmation dialog when delete button is clicked
            AlertDialog.Builder(it.context)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes") { _, _ ->
                    // Delete task using callback
                    onTaskDeleted(position)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    // Get the number of tasks in the list
    override fun getItemCount(): Int = tasks.size

    // Method to remove a task from the list
    fun removeTask(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }
}

