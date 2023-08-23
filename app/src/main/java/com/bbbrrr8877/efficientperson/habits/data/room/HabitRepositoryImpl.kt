package com.bbbrrr8877.efficientperson.habits.data.room

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitListDao: HabitListDao,
    private val mapper: HabitListMapper
) : HabitRepository {

    private val mutex = Mutex()

    override suspend fun addHabitItem(habitItem: HabitItem) {
        habitListDao.addHabitItem(mapper.mapEntityToDbModel(habitItem))
    }

    override suspend fun deleteHabitItem(habitItemId: Long) {
        habitListDao.deleteHabitItem(habitItemId)
    }

    override suspend fun editHabitItem(habitItem: HabitItem) {
        habitListDao.addHabitItem(mapper.mapEntityToDbModel(habitItem))
    }

    override fun getHabitItem(habitItemId: Long) = flow {
        habitListDao.getHabitItem(habitItemId).collect {
            val habitItem = mapper.mapDbModelToEntity(it)
            emit(habitItem)
        }
    }

    override fun getHabitList(): Flow<List<HabitItem>> = flow {
        habitListDao.getHabitList().collect {
            val habitItem = mapper.mapListDBModelToListEntity(it)
            emit(habitItem)
        }

    }


}