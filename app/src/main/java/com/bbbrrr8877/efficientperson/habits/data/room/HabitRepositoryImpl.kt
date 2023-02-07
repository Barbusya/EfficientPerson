package com.bbbrrr8877.efficientperson.habits.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import kotlin.random.Random

object HabitRepositoryImpl : HabitRepository {

    private val habitListLD = MutableLiveData<List<HabitItem>>()
    private val habitList = sortedSetOf<HabitItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrement = 0L

    init {
        for (i in 0 until 1000) {
            val item = HabitItem(
                "Name $i",
                "",
                Random.nextBoolean(),
                Random.nextBoolean()
            )
            addHabitItem(item)
        }
    }

    override fun addHabitItem(habitItem: HabitItem) {
        if (habitItem.id == HabitItem.UNDEFINED_ID) {
            habitItem.id = autoIncrement++
        }
        habitList.add(habitItem)
        updateList()
    }

    override fun deleteHabitItem(habitItem: HabitItem) {
        habitList.remove(habitItem)
    }

    override fun editHabitItem(habitItem: HabitItem) {
        val oldElement = getHabitItem(habitItem.id)
        habitList.remove(oldElement)
        addHabitItem(habitItem)
    }

    override fun getHabitItem(habitItemId: Long): HabitItem {
        return habitList.find {
            it.id == habitItemId
        } ?: throw RuntimeException("Element with id $habitItemId not found")
    }

    override fun getHabitList(): LiveData<List<HabitItem>> {
        return habitListLD
    }

    private fun updateList() {
        habitListLD.value = habitList.toList()
    }
}