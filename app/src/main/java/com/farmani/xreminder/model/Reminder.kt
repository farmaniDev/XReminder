package com.farmani.xreminder.model

import kotlinx.serialization.Serializable

@Serializable
data class Reminder(
    var title: String,
    var memo: String,
    var date: String,
    var time: String,
    var completed: Boolean
)
