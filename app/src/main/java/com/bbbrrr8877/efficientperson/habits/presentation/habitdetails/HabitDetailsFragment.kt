package com.bbbrrr8877.efficientperson.habits.presentation.habitdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitDetailsBinding

class HabitDetailsFragment : Fragment() {

    private lateinit var viewModel: HabitDetailsViewModel

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
