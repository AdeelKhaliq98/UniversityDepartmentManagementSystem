package com.example.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.Repositeries.StudentRepository
import com.example.StudentsDatabaseRecords.StudentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class StudentViewModel(repository: StudentRepository?) : ViewModel() {
    private var mRepository: StudentRepository? = null

    init {
        mRepository = repository
    }

    fun getAllStudents(local_id: Int): LiveData<List<StudentEntity>> {
        return mRepository!!.getAllStudents(local_id)
    }

    suspend fun deleteStudents(local_id: StudentEntity) {
        withContext(Dispatchers.IO) {
            mRepository!!.deleteStudents(local_id)
        }
    }
}