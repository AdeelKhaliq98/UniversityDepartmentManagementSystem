package com.example.StudentRecords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Repositeries.StudentRepository

class StudentAddTaskViewModelFactory(repository: StudentRepository?) :
    ViewModelProvider.NewInstanceFactory() {

    private var from_repository: StudentRepository? = null

    init {
        from_repository = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentAddTaskViewModel(from_repository!!) as T
    }

}