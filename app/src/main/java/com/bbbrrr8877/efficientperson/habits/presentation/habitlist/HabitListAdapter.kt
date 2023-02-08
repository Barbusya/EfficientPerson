package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitListAdapter : RecyclerView.Adapter<HabitItemViewHolder>() {

    var count = 0
    var habitList = listOf<HabitItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitItemViewHolder {
        Log.d("HabitListAdapter", "onCreateViewHolder, count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_GOOD_DONE -> R.layout.item_habit_bad_enabled
            VIEW_TYPE_GOOD_NOT_DONE -> R.layout.item_habit_good_disabled
            VIEW_TYPE_BAD_DONE -> R.layout.item_habit_bad_enabled
            VIEW_TYPE_BAD_NOT_DONE -> R.layout.item_habit_bad_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return HabitItemViewHolder(view)
    }

    override fun getItemCount() = habitList.size

    override fun onBindViewHolder(viewHolder: HabitItemViewHolder, position: Int) {
        val habitItem = habitList[position]
        viewHolder.view.setOnClickListener {
            true
        }
        viewHolder.tvTitle.text = habitItem.title
        viewHolder.cbPassedOrNot.isChecked = habitItem.isDone
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

    override fun getItemViewType(position: Int): Int {
        val item = habitList[position]
        return if (item.isGood && !item.isDone) {
            VIEW_TYPE_GOOD_NOT_DONE
        } else if (item.isGood && item.isDone) {
            VIEW_TYPE_GOOD_DONE
        } else if (!item.isGood && !item.isDone) {
            VIEW_TYPE_BAD_NOT_DONE
        } else {
            VIEW_TYPE_BAD_DONE
        }
    }

    companion object {
        const val VIEW_TYPE_GOOD_DONE = 200
        const val VIEW_TYPE_GOOD_NOT_DONE = 202
        const val VIEW_TYPE_BAD_DONE = 400
        const val VIEW_TYPE_BAD_NOT_DONE = 402

        const val MAX_POOL_SIZE = 15
    }
}