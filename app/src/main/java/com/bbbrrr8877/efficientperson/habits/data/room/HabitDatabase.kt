package com.bbbrrr8877.efficientperson.habits.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HabitItemDBModel::class], version = 1)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitListDao(): HabitListDao

    companion object {

        private var INSTANCE: HabitDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "habit_items.db"

        fun getInstance(context: Context): HabitDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
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