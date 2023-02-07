package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitListAdapter : ListAdapter<HabitItem, HabitItemViewHolder>(HabitItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HabitItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}