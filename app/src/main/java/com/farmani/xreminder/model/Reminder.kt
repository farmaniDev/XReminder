package com.farmani.xreminder.model

data class Reminder(
    var title: String,
    var memo: String,
    var date: String,
    var time: String,
    var completed: Boolean
)
