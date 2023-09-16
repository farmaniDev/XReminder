package com.farmani.xreminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.farmani.xreminder.R
import com.farmani.xreminder.adapter.ReminderAdapter
import com.farmani.xreminder.databinding.FragmentRemindersListBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// We define it global so other files have access to it
lateinit var remindersListBinding: FragmentRemindersListBinding

class RemindersListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        remindersListBinding = FragmentRemindersListBinding.inflate(inflater, container, false)
        return remindersListBinding.root
    }

    // This method is called once
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        remindersListBinding.addReminderBtn.setOnClickListener {
            Navigation.findNavController(remindersListBinding.addReminderBtn)
                .navigate(R.id.action_remindersListFragment_to_addReminderFragment)
        }
        lifecycleScope.launch {
            initRecyclerView()
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            initRecyclerView()
        }
    }

    private suspend fun initRecyclerView() {
        val adapter = ReminderAdapter(
            requireContext().dataStore.data.first().remindersList.toMutableList(),
            requireContext()
        )
        remindersListBinding.recyclerView.adapter = adapter
        remindersListBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}