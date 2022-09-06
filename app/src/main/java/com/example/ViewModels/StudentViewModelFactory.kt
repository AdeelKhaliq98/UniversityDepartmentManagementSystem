package com.example.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Repositeries.StudentRepository
import com.example.StudentRecords.StudentAddTaskViewModel

class StudentViewModelFactory (repository: StudentRepository?) :
    ViewModelProvider.NewInstanceFactory() {

    private var mRepository: StudentRepository? = null

    init {
        mRepository = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(mRepository!!) as T
    }

}
