package com.example.Helper

import android.content.Context
import com.example.Repositeries.DepartmentRepository
import com.example.UniversityDatabase.DepartmentDatabase
import com.example.ViewModels.DepartmentViewModelFactory
import com.example.ums.DepartmentAddTaskViewModelFactory

fun Context.provideDepartmentRepository(): DepartmentRepository? {
    val database = DepartmentDatabase.getInstance(applicationContext)
    return DepartmentRepository.getInstance(database!!.departDao())
}

fun Context.provideDepartmentAddTaskViewModelFactory(): DepartmentAddTaskViewModelFactory {
    return DepartmentAddTaskViewModelFactory(provideDepartmentRepository()!!)
}

fun Context.provideDepartmentViewModelFactory():DepartmentViewModelFactory{
    return DepartmentViewModelFactory(provideDepartmentRepository()!!)
}
