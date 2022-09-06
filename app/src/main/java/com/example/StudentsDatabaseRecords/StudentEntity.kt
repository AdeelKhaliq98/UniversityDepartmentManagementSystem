package com.example.StudentsDatabaseRecords

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.UniversityDatabase.DepartmentEntity

@Entity(
    tableName = "studentslist",
    foreignKeys = [ForeignKey(
        entity = DepartmentEntity::class,
        parentColumns = ["local_id"],
        childColumns = ["stdId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    var std_local_id: Int,
    var studentName: String,
    var studentRegNo: String,
    var studentAge: Int,
    var stdId: Int
)