package com.example.ums

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.Repositeries.DepartmentRepository
import com.example.UniversityDatabase.DepartmentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DepartmentAddTaskViewModel(repository: DepartmentRepository?) : ViewModel() {
    private var mRepository: DepartmentRepository? = null

    init {
        mRepository = repository
    }

    fun getDepartments(local_id: Int): LiveData<DepartmentEntity?>? {
        return mRepository!!.loadTaskById(local_id)
    }

    suspend fun insertDepartment(insert_Department: DepartmentEntity) {
        withContext(Dispatchers.IO) {
            return@withContext mRepository!!.insertDepartment(insert_Department)

        }
    }

    suspend fun updateDepartment(update_Department: DepartmentEntity) {
        withContext(Dispatchers.IO) {
            return@withContext mRepository!!.updateDepartment(update_Department)
        }
    }
}
