package com.example.UniversityDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departmentslist")
data class DepartmentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="local_id")
    var depart_local_id: Int,
    var departmentName: String,
    var departmentLocation: String
)
