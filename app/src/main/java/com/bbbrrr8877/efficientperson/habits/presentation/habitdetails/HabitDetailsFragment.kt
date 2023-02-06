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

    private val viewModel: HabitDetailsViewModel by lazy {
        ViewModelProvider(this)[HabitDetailsViewModel::class.java]
    }

    private var _binding: FragmentHabitDetailsBinding? = null
    private val binding: FragmentHabitDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentHabitDetailsBinding == null")

    private var screenMode: String = MODE_UNKNOWN
    private var habitItemId: Long = HabitItem.UNDEFINED_ID

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
        addTextChangeListener()
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

        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun launchEditMode() {
        viewModel.getHabitItem(habitItemId)
        viewModel.habitItem.observe(viewLifecycleOwner) {
            binding.etTitle.setText(it.title)
            binding.etDescription.setText(it.description)
            binding.swGoodOrBad.isGone
            if (it.isGood) {
                view?.setBackgroundColor(resources.getColor(R.color.good_bg_color))
            } else {
                binding.habitDetails.setBackgroundColor(resources.getColor(R.color.bad_bg_color))
            }
            if (it.isDone) {
                binding.cbPassedOrNot.isChecked
            }
        }
        binding.saveButton.setOnClickListener {
            viewModel.editHabitItem(
                binding.etTitle.text?.toString(),
                binding.etDescription.text?.toString(),
                binding.swGoodOrBad.isChecked,
                binding.cbPassedOrNot.isChecked,
            )
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addHabitItem(
                binding.etTitle.text?.toString(),
                binding.etDescription.text?.toString(),
                binding.swGoodOrBad.isChecked,
                binding.cbPassedOrNot.isChecked
            )
//            thread {
//                context?.contentResolver?.insert(
//                    Uri.parse("content://com.bbbrrr8877.android.shoppinglist/shop_items"),
//                    ContentValues().apply {
//                        put("id", 0)
//                        put("name", binding.etTitle.text?.toString())
//                        put("count", binding.etDescription.text?.toString()?.toInt())
//                        put("enabled", true)
//                    }
//                )
//            }
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Params screen mode is absent")
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

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val HABIT_ITEM_ID = "extra_habit_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
    }
}
