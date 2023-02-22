package com.bbbrrr8877.efficientperson.habits.data.room

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import javax.inject.Inject

class HabitListMapper @Inject constructor() {

    fun mapEntityToDbModel(habitItem: HabitItem) = HabitItemDBModel(
        id = habitItem.id,
        title = habitItem.title,
        description = habitItem.description,
        isGood = habitItem.isGood,
        isDone = habitItem.isDone,
    )

    fun mapDbModelToEntity(habitItemDBModel: HabitItemDBModel) = HabitItem(
        id = habitItemDBModel.id,
        title = habitItemDBModel.title,
        description = habitItemDBModel.description,
        isGood = habitItemDBModel.isGood,
        isDone = habitItemDBModel.isDone,
        )

    fun mapListDBModelToListEntity(list: List<HabitItemDBModel>) = list.map {
        mapDbModelToEntity(it)
    }
}