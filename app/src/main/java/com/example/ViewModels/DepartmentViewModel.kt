package com.example.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.Repositeries.DepartmentRepository
import com.example.Repositeries.StudentRepository
import com.example.UniversityDatabase.DepartmentDatabase
import com.example.UniversityDatabase.DepartmentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class DepartmentViewModel(repository: DepartmentRepository?) : ViewModel() {
    private var mRepository: DepartmentRepository? = null

    init {
        mRepository = repository
    }

    fun getDepartments(): LiveData<List<DepartmentEntity>> {
        return mRepository!!.getDepartments()
    }

    suspend fun deleteDepartment(local_id: DepartmentEntity) {
       withContext(Dispatchers.IO){
           mRepository!!.deleteDepartment(local_id)
       }
    }

}