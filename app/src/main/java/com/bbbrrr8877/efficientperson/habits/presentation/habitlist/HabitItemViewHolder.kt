package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbbrrr8877.efficientperson.R

class HabitItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView = view.findViewById(R.id.tv_title)
    val cbPassedOrNot: CheckBox = view.findViewById(R.id.cb_passed_or_not)
}