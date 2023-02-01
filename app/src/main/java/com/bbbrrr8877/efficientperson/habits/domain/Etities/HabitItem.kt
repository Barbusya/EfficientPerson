package com.bbbrrr8877.efficientperson.habits.domain.Etities

data class HabitItem(
    var id: Long = UNDEFINED_ID,
    val title: String,
    val description: String,
    val quality: Boolean,
    val mark: Boolean,
) {
    companion object {
        const val  UNDEFINED_ID = 0L
    }
}
