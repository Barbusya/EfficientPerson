package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitListAdapter : RecyclerView.Adapter<HabitItemViewHolder>() {

    var habitList = listOf<HabitItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_habit_good_disabled,
                parent,
                false
            )
        return HabitItemViewHolder(view)
    }

    override fun getItemCount() = habitList.size

    override fun onBindViewHolder(viewHolder: HabitItemViewHolder, position: Int) {
        val habitItem = habitList[position]
        val quality = if (habitItem.isGood) {
            "Good"
        } else {
            "Bad"
        }
        viewHolder.view.setOnClickListener {
            true
        }
        if (habitItem.isDone) {
            viewHolder.tvTitle.text = "${habitItem.title} $quality"
            viewHolder.cbPassedOrNot.isChecked = habitItem.isDone
            viewHolder.tvTitle.setTextColor(
                ContextCompat.getColor(
                    viewHolder.view.context,
                    android.R.color.holo_red_light
                )
            )
        }
    }

    override fun onViewRecycled(viewHolder: HabitItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvTitle.text = ""
        viewHolder.cbPassedOrNot.isChecked = false
        viewHolder.tvTitle.setTextColor(
            ContextCompat.getColor(
                viewHolder.view.context,
                android.R.color.white
            )
        )
    }

}