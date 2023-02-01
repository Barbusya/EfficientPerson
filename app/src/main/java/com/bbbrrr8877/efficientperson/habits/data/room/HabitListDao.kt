package com.bbbrrr8877.efficientperson.habits.data.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

@Dao
interface HabitListDao {

    @Query("SELECT * FROM habit_items")
    fun getHabitList(): LiveData<List<HabitItemDBModel>>

    @Query("SELECT * FROM habit_items")
    fun getHabitListCursor(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabitItem(habitItemDBModel: HabitItemDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHabitItemSync(habitItemDBModel: HabitItemDBModel)

    @Query("DELETE FROM habit_items WHERE id=:habitItemId")
    suspend fun deleteHabitItem(habitItemId: Long)

//    @Query("DELETE FROM habit_items WHERE id=:habitItemId")
//    fun deleteShopItemSync(habitItemId: Long): Long

    @Query("SELECT * FROM habit_items WHERE id=:habitItemId LIMIT 1")
    suspend fun getHabitItem(habitItemId: Long): HabitItemDBModel

}