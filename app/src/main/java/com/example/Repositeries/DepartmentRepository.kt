package com.example.Repositeries

import androidx.lifecycle.LiveData
import com.example.UniversityDatabase.DepartmentDao
import com.example.UniversityDatabase.DepartmentEntity

class DepartmentRepository(departmentDao: DepartmentDao?) {
    private var departmentDao: DepartmentDao? = departmentDao

    companion object {

        private val LOCK = Any()
        private var sInstance: DepartmentRepository? = null

        @Synchronized
        fun getInstance(
            departmentDao: DepartmentDao?,
        ): DepartmentRepository? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = DepartmentRepository(departmentDao)
                }
            }
            return sInstance
        }
    }

    suspend fun insertDepartment(insert_Department: DepartmentEntity) {
        departmentDao!!.insertDepartment(insert_Department)
    }

    suspend fun updateDepartment(update_Department: DepartmentEntity) {
        departmentDao!!.updateDepartment(update_Department)
    }

    fun getDepartments(): LiveData<List<DepartmentEntity>> {
        return departmentDao!!.getDepartments()
    }

    suspend fun deleteDepartment(local_id: DepartmentEntity) {
        departmentDao!!.deleteDepartment(local_id)
    }

    fun loadTaskById(local_id: Int): LiveData<DepartmentEntity?>? {
        return departmentDao!!.loadTaskById(local_id)
    }
}