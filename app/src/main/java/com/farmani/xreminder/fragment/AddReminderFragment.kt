package com.farmani.xreminder.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.farmani.xreminder.R
import com.farmani.xreminder.databinding.FragmentAddReminderBinding
import com.farmani.xreminder.db.RemindersListSerializer
import com.farmani.xreminder.model.Reminder
import com.farmani.xreminder.notification.Notification
import com.farmani.xreminder.utils.Picker
import com.farmani.xreminder.utils.date
import com.farmani.xreminder.utils.hour
import com.farmani.xreminder.utils.minute
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.launch

val Context.dataStore by dataStore("mainFile.json", RemindersListSerializer())

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

    @RequiresApi(Build.VERSION_CODES.O)
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

            // Update Data in Data Store
            lifecycleScope.launch {
                requireContext().dataStore.updateData {
                    it.copy(
                        it.remindersList.mutate {
                            it.add(newReminder)
                        }
                    )
                }

                Notification(
                    newReminder.title,
                    newReminder.memo,
                    requireContext(),
                    newReminder.hashCode().toString(),
                    newReminder
                )

                Navigation.findNavController(binding.saveBtn)
                    .navigate(R.id.action_addReminderFragment_to_remindersListFragment)
            }
        }
    }
}