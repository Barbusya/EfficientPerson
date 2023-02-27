package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bbbrrr8877.efficientperson.App
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitListBinding
import com.bbbrrr8877.efficientperson.habits.data.backgroundwork.HabitAlarmReceiver
import com.bbbrrr8877.efficientperson.habits.presentation.ViewModelFactory
import com.bbbrrr8877.efficientperson.habits.presentation.habitdetails.HabitDetailsFragment
import java.util.*
import javax.inject.Inject

class HabitListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var habitListAdapter: HabitListAdapter

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HabitListViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentHabitListBinding? = null
    private val binding: FragmentHabitListBinding
        get() = _binding ?: throw RuntimeException("FragmentHabitListBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)

        super.onAttach(context)
    }

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

    private fun isOnePaneMode(orientation: Boolean): Int {
        return if (orientation) {
            R.id.main_container
        } else {
            R.id.land_fragment_container
        }
    }

    private fun launchFragment(fragment: Fragment) {
        val layout = isOnePaneMode(binding.landFragmentContainer == null)
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(layout, fragment)
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
        setCheckBoxClickListener()
    }

    private fun setupClickListener() {
        habitListAdapter.onHabitItemClickListener = {
            launchEditHabitItemFragment(it.id)
        }
    }


    private fun setCheckBoxClickListener() {
        habitListAdapter.onCheckBoxItemClickListener = {
            viewModel.changeDoneState(it)

//            SimpleWorker.startWork(requireActivity().applicationContext)
            val alarmManager = requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 20)
            calendar.set(Calendar.SECOND, 0)
//            calendar.add(Calendar.SECOND, 10)
            val intent = HabitAlarmReceiver.newIntent(requireActivity().applicationContext)
            val pendingIntent = PendingIntent.getBroadcast(
                requireActivity().applicationContext,
                100,
                intent,
                0
            )
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    private fun launchEditHabitItemFragment(habitItemId: Long) {
        launchFragment(HabitDetailsFragment.newInstanceEditItem(habitItemId))
    }

    private fun launchAddHabitItemFragment() {
        launchFragment(HabitDetailsFragment.newInstanceAddItem())
    }

    //TODO(Check requireActivity().applicationContext)
    //TODO(Check flags in PendingIntent.getBroadcast)
    //TODO Refactor AlarmManager
}
