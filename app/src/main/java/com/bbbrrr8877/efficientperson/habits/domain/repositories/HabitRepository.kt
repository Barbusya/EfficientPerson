package com.bbbrrr8877.efficientperson.habits.domain.repositories

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun addHabitItem(habitItem: HabitItem)

    suspend fun deleteHabitItem(habitItem: HabitItem)

    suspend fun editHabitItem(habitItem: HabitItem)

    fun getHabitItem(habitItemId: Long): Flow<HabitItem>

    fun getHabitList(): Flow<List<HabitItem>>
}