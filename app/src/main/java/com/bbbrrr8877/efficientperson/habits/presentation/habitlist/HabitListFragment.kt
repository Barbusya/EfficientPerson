package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitDetailsBinding
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitListBinding

class HabitListFragment: Fragment() {

    private var count = 0

    private lateinit var viewModel: HabitListViewModel

    private var _binding: FragmentHabitListBinding? = null
    private val binding: FragmentHabitListBinding
        get() = _binding ?: throw RuntimeException("FragmentHabitListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HabitListViewModel::class.java]
        viewModel.habitList.observe(viewLifecycleOwner) {
            Log.d("HabitListFragment", it.toString())
            if(count == 0) {
                count++
                val item = it[0]
                viewModel.changeDoneState(item)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
// 3 - 4
}
