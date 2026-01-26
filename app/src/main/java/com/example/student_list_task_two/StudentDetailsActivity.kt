package com.example.student_list_task_two

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_list_task_two.databinding.ActivityStudentDetailsBinding
import com.example.student_list_task_two.databinding.ActivityStudentsListBinding
import com.example.student_list_task_two.models.Model

class StudentDetailsActivity : AppCompatActivity() {
    var binding : ActivityStudentDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding?.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val index = intent.getIntExtra("STUDENT_INDEX", -1)
        if (index == -1) {
            finish()
            return
        }

        val student = Model.shared.students[index]
        binding?.studentDetailsName?.text = "Name: ${student.name}"
        binding?.studentDetailsId?.text = "ID: ${student.id}"
        binding?.studentDetailsPhone?.text = "Phone: ${student.phone}"
        binding?.studentDetailsAddress?.text = "Address: ${student.address}"
        binding?.studentDetailsCheckbox?.isChecked = student.isChecked ?: false

        binding?.studentDetailsEditBtn?.setOnClickListener {
            // TODO - Navigate to an EditActivity
        }
    }
}