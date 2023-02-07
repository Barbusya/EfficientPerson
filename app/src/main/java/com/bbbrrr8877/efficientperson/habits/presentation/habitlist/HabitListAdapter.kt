package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitListAdapter : RecyclerView.Adapter<HabitItemViewHolder>() {

    val list = listOf<HabitItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit_good_disabled, parent, false)
        return HabitItemViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(viewHolder: HabitItemViewHolder, position: Int) {
        val habitItem = list[position]
        viewHolder.tvTitle.text = habitItem.title
        viewHolder.cbPassedOrNot.isChecked = habitItem.isDone
        viewHolder.view.setOnClickListener {
            true
        }
    }

}