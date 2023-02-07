package com.bbbrrr8877.efficientperson.habits.presentation.habitdetails

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitDetailsBinding
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem

class HabitDetailsFragment : Fragment() {

    private var _binding: FragmentHabitDetailsBinding? = null
    private val binding: FragmentHabitDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentHabitDetailsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
