package com.farmani.xreminder.db

import com.farmani.xreminder.model.Reminder
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable


// Made for DataStore
@Serializable
data class RemindersListHolder(
    @Serializable(ReminderListSerializer::class)
    val remindersList: PersistentList<Reminder> = persistentListOf()
)
