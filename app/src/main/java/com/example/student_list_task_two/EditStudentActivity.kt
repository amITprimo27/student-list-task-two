package com.example.student_list_task_two

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_list_task_two.databinding.ActivityAddStudentBinding
import com.example.student_list_task_two.databinding.ActivityEditStudentBinding
import com.example.student_list_task_two.models.Model
import com.example.student_list_task_two.models.Student


class EditStudentActivity : AppCompatActivity() {
    var binding: ActivityEditStudentBinding? = null
    var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        this.studentIndex = intent.getIntExtra("STUDENT_INDEX", -1)

        if (this.studentIndex == -1) {
            this.returnHome()
            return
        }

        val student = Model.shared.students[this.studentIndex]

        this.binding?.nameEditText?.setText(student.name ?: "")
        this.binding?.idEditText?.setText(student.id ?: "")
        this.binding?.phoneEditText?.setText(student.phone ?: "")
        this.binding?.addressEditText?.setText(student.address ?: "")
        this.binding?.checked?.isChecked = student.isChecked ?: false
    }

    fun onCancel(view: View) {
        finish()
    }

    fun onSave(view: View) {
        val updatedStudent = Student(
            name = this.binding?.nameEditText?.text.toString(),
            phone = this.binding?.phoneEditText?.text.toString(),
            address = this.binding?.addressEditText?.text.toString(),
            id = this.binding?.idEditText?.text.toString(),
            isChecked = this.binding?.checked?.isChecked ?: false
        )
        Model.shared.students[this.studentIndex] = updatedStudent
        returnHome()
    }

    fun onDelete(view: View) {
        Model.shared.students.removeAt(this.studentIndex)
        returnHome()
    }

    private fun returnHome() {
        val intent: Intent = Intent(this, StudentsListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

}