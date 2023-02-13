package com.bbbrrr8877.efficientperson.habits.domain.usecases

import androidx.lifecycle.LiveData
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import javax.inject.Inject

class GetHabitListUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {

    fun getHabitList(): LiveData<List<HabitItem>> {
        return habitRepository.getHabitList()
    }
}