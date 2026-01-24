package com.example.student_list_task_two

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.student_list_task_two.databinding.ActivityStudentsListBinding
import com.example.student_list_task_two.models.Model

class StudentsListActivity : AppCompatActivity() {
    var binding : ActivityStudentsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layout = LinearLayoutManager(this)
        binding?.recyclerView?.layoutManager = layout
        binding?.recyclerView?.setHasFixedSize(true)

        val adapter = StudentsAdapter(Model.shared.students)
        binding?.recyclerView?.adapter = adapter
    }

    fun onAddStudent(view: View) {
        startActivity(Intent(this, AddStudentActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        binding?.recyclerView?.adapter?.notifyDataSetChanged()
    }
}