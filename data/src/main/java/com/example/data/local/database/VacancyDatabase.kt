package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.dao.VacancyDao
import com.example.data.local.dto.VacancyDbEntity
import com.example.domain.utils.DatabaseConstants

@Database(
    version = 1,
    entities = [VacancyDbEntity::class]
)
@TypeConverters(Converters::class)
abstract class VacancyDatabase : RoomDatabase() {

    abstract fun vacancyDao(): VacancyDao

    companion object {
        private const val DATABASE_NAME = DatabaseConstants.DATABASE_NAME
        private lateinit var INSTANCE: VacancyDatabase

        @Synchronized
        operator fun invoke(context: Context): VacancyDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    VacancyDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
            }
            return INSTANCE
        }
    }
}
