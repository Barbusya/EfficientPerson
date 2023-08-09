package com.bbbrrr8877.efficientperson.habits.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitListDao {

    @Query("SELECT * FROM habit_items")
    fun getHabitList(): Flow<List<HabitItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabitItem(habitItemDBModel: HabitItemDBModel)

    @Query("DELETE FROM habit_items WHERE id=:habitItemId")
    suspend fun deleteHabitItem(habitItemId: Long)

    @Query("SELECT * FROM habit_items WHERE id=:habitItemId LIMIT 1")
    fun getHabitItem(habitItemId: Long): Flow<HabitItemDBModel>

}