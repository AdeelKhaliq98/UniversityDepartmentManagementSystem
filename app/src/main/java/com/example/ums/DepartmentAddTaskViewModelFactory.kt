package com.example.ums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Repositeries.DepartmentRepository

class DepartmentAddTaskViewModelFactory(repository: DepartmentRepository?) :
    ViewModelProvider.NewInstanceFactory() {

    private var mRepository: DepartmentRepository? = null

    init {
        mRepository = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DepartmentAddTaskViewModel(mRepository!!) as T
    }
}