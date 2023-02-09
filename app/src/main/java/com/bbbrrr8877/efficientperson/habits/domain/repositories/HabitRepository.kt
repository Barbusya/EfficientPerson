package com.bbbrrr8877.efficientperson.habits.domain.repositories

import androidx.lifecycle.LiveData
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

interface HabitRepository {

    fun addHabitItem(habitItem: HabitItem)

    fun deleteHabitItem(habitItem: HabitItem)

    fun editHabitItem(habitItem: HabitItem)

    fun getHabitItem(habitItemId: Long): HabitItem

    fun getHabitList(): LiveData<List<HabitItem>>
}