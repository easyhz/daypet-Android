package com.easyhz.daypet.data.model.request.memory

import com.google.firebase.Timestamp

data class MemoryRequest(
    val startDate: Timestamp,
    val endDate: Timestamp,
    val groupId: String,
)
