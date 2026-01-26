package com.example.student_list_task_two.models

class Model private constructor(){
    val students = mutableListOf<Student>()

    companion object {
        val shared = Model()
    }
}