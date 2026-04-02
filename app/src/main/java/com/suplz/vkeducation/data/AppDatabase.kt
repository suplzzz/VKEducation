package com.suplz.vkeducation.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suplz.vkeducation.data.appdetails.local.AppDetailsDao
import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntity

@Database(
    entities = [AppDetailsEntity::class],
    version = 1,
)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }

}