package com.bbbrrr8877.efficientperson.habits.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_items")
data class HabitItemDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val isGood: Boolean,
    val isDone: Boolean,
    val deleteTime: Long,
)