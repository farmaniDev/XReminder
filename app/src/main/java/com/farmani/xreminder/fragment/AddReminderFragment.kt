package com.farmani.xreminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.farmani.xreminder.R
import com.farmani.xreminder.databinding.FragmentAddReminderBinding
import com.farmani.xreminder.model.Reminder
import com.farmani.xreminder.utils.Picker
import com.farmani.xreminder.utils.date
import com.farmani.xreminder.utils.hour
import com.farmani.xreminder.utils.minute

class AddReminderFragment : Fragment() {
    private lateinit var binding: FragmentAddReminderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateTimeInputET.setOnClickListener {
            Picker(parentFragmentManager, binding.dateTimeInputET)
        }
        binding.saveBtn.setOnClickListener {
            val newReminder = Reminder(
                binding.titleEditText.editText?.text.toString(),
                binding.memoEditText.editText?.text.toString(),
                date,
                "$hour:$minute",
                false
            )
            remindersList.add(newReminder)
            Navigation.findNavController(binding.saveBtn)
                .navigate(R.id.action_addReminderFragment_to_remindersListFragment)
        }
    }
}