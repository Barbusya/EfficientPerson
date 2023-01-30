package com.bbbrrr8877.efficientperson.habits.domain

data class HabitItem(
    var id: Long = UNDEFINED_ID,
    val name: String,
    val description: String,
    val quality: Boolean,
    val mark: Boolean,
) {
    companion object {
        const val  UNDEFINED_ID = 0L
    }
}
