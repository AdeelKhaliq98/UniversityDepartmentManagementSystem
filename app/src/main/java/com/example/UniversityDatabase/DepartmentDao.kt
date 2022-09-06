package com.example.UniversityDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DepartmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartment(insert_Department: DepartmentEntity)

    @Query("Select * from  departmentslist order by local_id asc ")
    fun getDepartments(): LiveData<List<DepartmentEntity>>

    @Update
    suspend fun updateDepartment(update_Department: DepartmentEntity)

    @Delete
    suspend fun deleteDepartment(local_id :DepartmentEntity)

    @Query("SELECT * FROM departmentslist WHERE local_id = :local_id")
    fun loadTaskById(local_id:Int): LiveData<DepartmentEntity?>
}