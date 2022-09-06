package com.example.UniversityDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.StudentsDatabaseRecords.StudentDao
import com.example.StudentsDatabaseRecords.StudentEntity


@Database(entities = [DepartmentEntity::class,StudentEntity::class], version = 1, exportSchema = false)
abstract class DepartmentDatabase : RoomDatabase() {
    abstract fun departDao(): DepartmentDao?
    abstract fun studentDao():StudentDao?

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "DepartmentDB"
        private var sInstance: DepartmentDatabase? = null
        fun getInstance(context: Context): DepartmentDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        DepartmentDatabase::class.java, DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return sInstance
        }
    }
}