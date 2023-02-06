package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitListAdapter : ListAdapter<HabitItem, HabitItemViewHolder>(HabitItemDiffCallback()) {

    var onItemClickListener: ((HabitItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_GOOD_DONE -> R.layout.item_habit_good_enabled
            VIEW_TYPE_GOOD_NOT_YET -> R.layout.item_habit_good_disabled
            VIEW_TYPE_BAD_DONE -> R.layout.item_habit_bad_enabled
            VIEW_TYPE_BAD_NOT_YET -> R.layout.item_habit_bad_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return HabitItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: HabitItemViewHolder, position: Int) {
        val habitItem = getItem(position)
        viewHolder.view.setOnClickListener {
            onItemClickListener?.invoke(habitItem)
        }

        viewHolder.tvTitle.text = "${habitItem.title}"
        viewHolder.cbPassedOrNot.isChecked = habitItem.isDone

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isGood and item.isDone) {
            VIEW_TYPE_GOOD_DONE
        } else if (item.isGood and !item.isDone) {
            VIEW_TYPE_GOOD_NOT_YET
        } else if (!item.isGood and item.isDone) {
            VIEW_TYPE_BAD_DONE
        } else {
            VIEW_TYPE_BAD_NOT_YET
        }
    }

    companion object {
        const val VIEW_TYPE_GOOD_DONE = 200
        const val VIEW_TYPE_GOOD_NOT_YET = 201
        const val VIEW_TYPE_BAD_DONE = 400
        const val VIEW_TYPE_BAD_NOT_YET = 401
    }
}