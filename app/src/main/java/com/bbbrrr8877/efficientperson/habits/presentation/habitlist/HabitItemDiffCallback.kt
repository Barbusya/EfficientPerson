package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import androidx.recyclerview.widget.DiffUtil
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitItemDiffCallback : DiffUtil.ItemCallback<HabitItem>() {
    override fun areItemsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
        return oldItem == newItem
    }

}