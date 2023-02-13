package com.bbbrrr8877.efficientperson.habits.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitListDao: HabitListDao,
    private val mapper: HabitListMapper
) : HabitRepository {

    override suspend fun addHabitItem(habitItem: HabitItem) {
        habitListDao.addHabitItem(mapper.mapEntityToDbModel(habitItem))
    }

    override suspend fun deleteHabitItem(habitItem: HabitItem) {
        habitListDao.deleteHabitItem(habitItem.id)
    }

    override suspend fun editHabitItem(habitItem: HabitItem) {
        habitListDao.addHabitItem(mapper.mapEntityToDbModel(habitItem))
    }

    override suspend fun getHabitItem(habitItemId: Long): HabitItem {
        val dbModel = habitListDao.getHabitItem(habitItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getHabitList(): LiveData<List<HabitItem>> = Transformations.map(
        habitListDao.getHabitList()
    ) {
        mapper.mapListDBModelToListEntity(it)
    }
}