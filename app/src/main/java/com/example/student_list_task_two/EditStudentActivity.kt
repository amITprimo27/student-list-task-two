package com.example.student_list_task_two

import android.content.Intent
import android.os.Bundle
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
    var id: String? = null

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


        this.id = intent.getStringExtra("STUDENT_ID")

        if (this.id == null) {
            this.returnHome()
            return
        }

        val student = Model.shared.students.find { it.id ==  this.id }

        if (student == null) {
            this.returnHome()
            return
        }

        this.binding?.nameTextView?.text = student.name
        this.binding?.idTextView?.text = student.id
        this.binding?.phoneTextView?.text = student.phone
        this.binding?.addressTextView?.text = student.address
        this.binding?.checked?.isChecked = student.isChecked
    }

    fun getStudentIndex(): Int {
        return Model.shared.students.indexOfFirst { it.id == this.id }
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
        Model.shared.students[this.getStudentIndex()] = updatedStudent
        returnHome()
    }

    fun onDelete(view: View) {
        Model.shared.students.removeAt(this.getStudentIndex())
        returnHome()
    }

    private fun returnHome() {
        val intent: Intent = Intent(this, StudentsListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

}