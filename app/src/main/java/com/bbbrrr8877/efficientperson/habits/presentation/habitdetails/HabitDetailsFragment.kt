package com.bbbrrr8877.efficientperson.habits.presentation.habitdetails

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bbbrrr8877.efficientperson.App
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.databinding.FragmentHabitDetailsBinding
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.presentation.ViewModelFactory
import javax.inject.Inject

class HabitDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)

        super.onAttach(context)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HabitDetailsViewModel::class.java]
    }

    private var _binding: FragmentHabitDetailsBinding? = null
    private val binding: FragmentHabitDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentHabitDetailsBinding == null")

    private var screenMode: String = MODE_UNKNOWN
    private var habitItemId: Long = HabitItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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

        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.errorInputTitle.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_title)
            } else {
                null
            }
            binding.tilTitle.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            backToList()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputTitle()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun launchEditMode() {
        viewModel.getHabitItem(habitItemId)
        viewModel.habitItem.observe(viewLifecycleOwner) {
            with(binding) {
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                cbPassedOrNot.isChecked = it.isDone
                cbPassedOrNot.setOnClickListener {
                    if (binding.cbPassedOrNot.isChecked) {
                        binding.tvPassedOrNot.setText(R.string.passed)
                    } else {
                        binding.tvPassedOrNot.setText(R.string.not_passed)
                    }
                }
                swGoodOrBad.visibility = View.INVISIBLE
                if (it.isGood) {
                    tvGoodOrBad.setText(R.string.good_habit)
                    binding.habitDetails.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.good_bg_color
                        )
                    )
                } else {
                    tvGoodOrBad.setText(R.string.bad_habit)
                    binding.habitDetails.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.bad_bg_color
                        )
                    )
                }

                tvPassedOrNot.setText(
                    if (it.isDone) {
                        R.string.passed
                    } else {
                        R.string.not_passed
                    }
                )
            }

        }
        binding.saveButton.setOnClickListener {
            viewModel.editHabitItem(
                binding.etTitle.text?.toString(),
                binding.etDescription.text?.toString(),
                binding.cbPassedOrNot.isChecked
            )
        }
    }

    private fun launchAddMode() {
        with(binding) {
            cbPassedOrNot.visibility = View.INVISIBLE
            cbPassedOrNot.setOnClickListener {
                if (binding.cbPassedOrNot.isChecked) {
                    binding.tvPassedOrNot.setText(R.string.passed)
                } else {
                    binding.tvPassedOrNot.setText(R.string.not_passed)
                }
            }
            swGoodOrBad.setOnClickListener {
                if (binding.swGoodOrBad.isChecked) {
                    binding.tvGoodOrBad.setText(R.string.good_habit)
                    binding.habitDetails.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.good_bg_color
                        )
                    )
                } else {
                    binding.tvGoodOrBad.setText(R.string.bad_habit)
                    binding.habitDetails.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.bad_bg_color
                        )
                    )
                }
            }
        }

        binding.saveButton.setOnClickListener {
            viewModel.addHabitItem(
                binding.etTitle.text?.toString(),
                binding.etDescription.text?.toString(),
                binding.swGoodOrBad.isChecked,
                binding.cbPassedOrNot.isChecked
            )
        }
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(HABIT_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            habitItemId = args.getLong(HABIT_ITEM_ID, HabitItem.UNDEFINED_ID)
        }
    }

    private fun backToList() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object {
        private const val SCREEN_MODE = "mode"
        private const val HABIT_ITEM_ID = "habit_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): HabitDetailsFragment {
            return HabitDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(habitItemId: Long): HabitDetailsFragment {
            return HabitDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putLong(HABIT_ITEM_ID, habitItemId)
                }
            }
        }
    }
}
