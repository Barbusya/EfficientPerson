package com.bbbrrr8877.efficientperson.habits.domain.Etities

data class HabitItem(
    val title: String,
    val description: String = "",
    val isGood: Boolean,
    val isDone: Boolean,
    val deleteTime: Long,
    var id: Long = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0L
    }
}
