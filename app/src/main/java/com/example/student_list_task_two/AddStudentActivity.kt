package com.example.student_list_task_two

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_list_task_two.databinding.ActivityAddStudentBinding
import com.example.student_list_task_two.models.Model
import com.example.student_list_task_two.models.Student

class AddStudentActivity : AppCompatActivity() {
    var binding: ActivityAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onCancel(view: View) {
        finish()
    }

    fun onSave(view: View) {
        val student = Student(
            name = this.binding?.nameEditText?.text.toString(),
            phone = this.binding?.phoneEditText?.text.toString(),
            address = this.binding?.addressEditText?.text.toString(),
            id = this.binding?.idEditText?.text.toString(),
            isChecked = this.binding?.checked?.isChecked ?: false
        )
        Model.shared.students.add(student)
        finish()
    }
}