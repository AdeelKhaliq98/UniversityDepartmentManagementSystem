package com.example.StudentRecords

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.Repositeries.StudentRepository
import com.example.StudentsDatabaseRecords.StudentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentAddTaskViewModel(repository: StudentRepository?) : ViewModel() {
    private var from_repository: StudentRepository? = null

    init {
        from_repository = repository
    }

    fun getStudents(local_id: Int): LiveData<StudentEntity?>? {
        return from_repository!!.loadTaskById(local_id)
    }

    suspend fun insertStudents(insertStudent: StudentEntity) {
        withContext(Dispatchers.IO) {
            return@withContext from_repository!!.insertStudents(insertStudent)
        }
    }

    suspend fun updateStudents(updateStudent: StudentEntity) {
        withContext(Dispatchers.IO) {
            return@withContext from_repository!!.updateStudents(updateStudent)
        }
    }
}