package com.example.student_list_task_two

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.student_list_task_two.databinding.StudentRowLayoutBinding
import com.example.student_list_task_two.models.Student

class StudentsAdapter(
    private var students: List<Student>,
): RecyclerView.Adapter<StudentRowViewHolder>() {

    override fun getItemCount(): Int = students.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRowViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = StudentRowLayoutBinding.inflate(inflator, parent, false)
        return StudentRowViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: StudentRowViewHolder, position: Int) {
        holder.bind(students[position], position)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_INDEX", position)
            holder.itemView.context.startActivity(intent)
        }
    }
}