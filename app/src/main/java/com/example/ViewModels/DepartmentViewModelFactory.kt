package com.example.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Repositeries.DepartmentRepository
import com.example.ums.DepartmentAddTaskViewModel

class DepartmentViewModelFactory(repository: DepartmentRepository?) :
    ViewModelProvider.NewInstanceFactory() {

    private var mRepository: DepartmentRepository? = null

    init {
        mRepository = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DepartmentViewModel(mRepository!!) as T
    }
}