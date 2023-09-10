package com.farmani.xreminder.utils

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class Picker(var context: Context, var fragmentManager: FragmentManager) {
    init {
        makeDatePicker()
        makeTimePicker()
    }

    private fun makeDatePicker() {
        TODO("Not yet implemented")
    }

    private fun makeTimePicker() {
        val timePicker: MaterialTimePicker =
            MaterialTimePicker.Builder().setTitleText("Select Time")
                .setTimeFormat(TimeFormat.CLOCK_24H).build()
        timePicker.show(fragmentManager, "reminderTimePicker")
        timePicker.addOnPositiveButtonClickListener {
            hour = timePicker.hour
            minute = timePicker.minute
        }
    }
}