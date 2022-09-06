package com.example.Helper

import android.content.Context
import com.example.Repositeries.StudentRepository
import com.example.StudentRecords.StudentAddTaskViewModelFactory
import com.example.UniversityDatabase.DepartmentDatabase
import com.example.ViewModels.StudentViewModelFactory

fun Context.provideStudentRepository(): StudentRepository? {
    val database = DepartmentDatabase.getInstance(applicationContext)
    return StudentRepository.getInstance(database!!.studentDao())
}

fun Context.provideStudentAddTaskViewModelFactory(): StudentAddTaskViewModelFactory {
    return StudentAddTaskViewModelFactory(provideStudentRepository()!!)
}

fun Context.provideStudentViewModelFactory():StudentViewModelFactory{
    return StudentViewModelFactory(provideStudentRepository())
}

