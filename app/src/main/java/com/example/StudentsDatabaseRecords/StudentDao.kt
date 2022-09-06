package com.example.StudentsDatabaseRecords

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(insertStudent: StudentEntity)

    @Query("Select * from  studentslist where stdId=:local_id ")
    fun getAllStudents(local_id:Int): LiveData<List<StudentEntity>>

    @Update
    suspend fun updateStudents(updateStudent: StudentEntity)

    @Delete
    suspend fun deleteStudents(local_id: StudentEntity)

    @Query("SELECT * FROM studentslist WHERE std_local_id = :local_id")
    fun loadTaskById(local_id: Int): LiveData<StudentEntity?>
}