package com.farmani.xreminder.utils

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Picker(private var fragmentManager: FragmentManager) {
    init {
        makeDatePicker()
        makeTimePicker()
    }

    private fun makeDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build()
        datePicker.show(fragmentManager, "reminderDatePicker")
        datePicker.addOnPositiveButtonClickListener {
            val outputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            outputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            date = outputDateFormat.format(it)
        }

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