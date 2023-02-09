package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitListBinding
import com.bbbrrr8877.efficientperson.habits.presentation.habitdetails.HabitDetailsFragment

class HabitListFragment : Fragment() {

    private lateinit var viewModel: HabitListViewModel
    private lateinit var habitListAdapter: HabitListAdapter

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
            habitListAdapter.submitList(it)
        }
        binding.buttonAddHabit.setOnClickListener {
            launchAddHabitItemFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        val rvHabitList = binding.rvHabitList
        with(rvHabitList) {
            habitListAdapter = HabitListAdapter()
            adapter = habitListAdapter
            recycledViewPool.setMaxRecycledViews(
                HabitListAdapter.VIEW_TYPE_GOOD_NOT_DONE,
                HabitListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                HabitListAdapter.VIEW_TYPE_GOOD_DONE,
                HabitListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                HabitListAdapter.VIEW_TYPE_BAD_NOT_DONE,
                HabitListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                HabitListAdapter.VIEW_TYPE_BAD_DONE,
                HabitListAdapter.MAX_POOL_SIZE
            )
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        habitListAdapter.onHabitItemClickListener = {
            Log.d("HabitListFragment", it.toString())
            launchEditHabitItemFragment(it.id)
        }
    }

    private fun launchEditHabitItemFragment(habitItemId: Long) {
        habitListAdapter.onHabitItemClickListener = {
            launchFragment(HabitDetailsFragment.newInstanceEditItem(it.id))
        }
    }

    private fun launchAddHabitItemFragment() {
        launchFragment(HabitDetailsFragment.newInstanceAddItem())
    }
}
