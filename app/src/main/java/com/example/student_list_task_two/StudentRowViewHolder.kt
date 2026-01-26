package com.example.student_list_task_two

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.student_list_task_two.databinding.StudentRowLayoutBinding
import com.example.student_list_task_two.models.Student

class StudentRowViewHolder(
    private val binding: StudentRowLayoutBinding
): RecyclerView.ViewHolder(binding.root) {

    private var student: Student? = null

    init {
        binding.checkbox.setOnClickListener { view ->
            (view?.tag as? Int)?.let { tag ->
                student?.isChecked = binding.checkbox.isChecked
            }
        }
    }

    fun bind(student: Student, position: Int) {
        this.student = student
        binding.nameTextView.text = student.name
        binding.idTextView.text = student.id
        binding.checkbox.apply {
            isChecked = student.isChecked ?: false
            tag = position
        }
    }
}