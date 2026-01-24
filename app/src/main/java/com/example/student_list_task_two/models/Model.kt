package com.example.student_list_task_two.models

class Model private constructor(){
    val students = mutableListOf<Student>()

    init {
        for (i in 1..20) {
            val student = Student(
                name = "Student $i",
                id = "ID${1000 + i}",
                isChecked = false,
                phone = "555-010${i.toString().padStart(2, '0')}",
                address = "123 Main St, City $i"
            )
            students.add(student)
        }
    }
    companion object {
        val shared = Model()
    }
}