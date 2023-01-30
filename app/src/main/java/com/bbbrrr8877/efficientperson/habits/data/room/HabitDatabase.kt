package com.bbbrrr8877.efficientperson.habits.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HabitItemDBModel::class], version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitListDao(): HabitListDao

    companion object {

        private var INSTANCE: HabitDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "habit_items.db"

        fun getInstance(application: Application): HabitDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    HabitDatabase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

}