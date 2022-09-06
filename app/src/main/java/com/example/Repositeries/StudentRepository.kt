package com.example.Repositeries

import androidx.lifecycle.LiveData
import com.example.StudentsDatabaseRecords.StudentDao
import com.example.StudentsDatabaseRecords.StudentEntity

class StudentRepository(studentDao: StudentDao?) {
    private var studentDao: StudentDao? = studentDao

    companion object {

        private val LOCK = Any()
        private var sInstance: StudentRepository? = null

        @Synchronized
        fun getInstance(
            studentDao: StudentDao?,
        ): StudentRepository? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = StudentRepository(studentDao)
                }
            }
            return sInstance
        }
    }

    suspend fun insertStudents(insertStudent: StudentEntity) {
        studentDao!!.insertStudents(insertStudent)
    }

    suspend fun updateStudents(updateStudent: StudentEntity) {
        studentDao!!.updateStudents(updateStudent)
    }

    fun getAllStudents(local_id: Int): LiveData<List<StudentEntity>> {
        return studentDao!!.getAllStudents(local_id)
    }

    suspend fun deleteStudents(local_id: StudentEntity) {
        studentDao!!.deleteStudents(local_id)
    }

    fun loadTaskById(local_id: Int): LiveData<StudentEntity?>? {
        return studentDao!!.loadTaskById(local_id)
    }
}