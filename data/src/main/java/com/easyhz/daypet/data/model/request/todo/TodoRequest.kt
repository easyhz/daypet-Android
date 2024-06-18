package com.easyhz.daypet.data.model.request.todo

import com.google.firebase.Timestamp

data class TodoRequest(
    val startDate: Timestamp,
    val endDate: Timestamp,
)
