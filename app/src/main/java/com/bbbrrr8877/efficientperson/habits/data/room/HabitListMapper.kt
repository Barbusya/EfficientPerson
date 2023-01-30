package com.bbbrrr8877.efficientperson.habits.data.room

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitListMapper {

    fun mapEntityToDbModel(habitItem: HabitItem) = HabitItemDBModel(
        id = habitItem.id,
        name = habitItem.name,
        description = habitItem.description,
        quality = habitItem.quality,
        mark = habitItem.mark,
    )

    fun mapDbModelToEntity(habitItemDBModel: HabitItemDBModel) = HabitItem(
        id = habitItemDBModel.id,
        name = habitItemDBModel.name,
        description = habitItemDBModel.description,
        quality = habitItemDBModel.quality,
        mark = habitItemDBModel.mark,
    )

    fun mapListDBModelToListEntity(list: List<HabitItemDBModel>) = list.map {
        mapDbModelToEntity(it)
    }
}