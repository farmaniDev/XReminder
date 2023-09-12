package com.farmani.xreminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.farmani.xreminder.R
import com.farmani.xreminder.adapter.ReminderAdapter
import com.farmani.xreminder.databinding.FragmentRemindersListBinding
import com.farmani.xreminder.model.Reminder

val remindersList = mutableListOf<Reminder>()

class RemindersListFragment : Fragment() {
    private lateinit var binding: FragmentRemindersListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemindersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // This method is called once
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addReminderBtn.setOnClickListener {
            Navigation.findNavController(binding.addReminderBtn)
                .navigate(R.id.action_remindersListFragment_to_addReminderFragment)
        }
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = ReminderAdapter(remindersList, requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}