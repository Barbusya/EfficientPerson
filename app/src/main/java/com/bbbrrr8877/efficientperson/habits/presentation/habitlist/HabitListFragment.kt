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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitDetailsBinding
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitListBinding

class HabitListFragment: Fragment() {

    private lateinit var viewModel: HabitListViewModel
    private lateinit var adapter: HabitListAdapter

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

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[HabitListViewModel::class.java]
        viewModel.habitList.observe(viewLifecycleOwner) {
           adapter.habitList = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val rvHabitList = binding.rvHabitList
        adapter = HabitListAdapter()
        rvHabitList.adapter = adapter
    }
// 7 - 8
}
