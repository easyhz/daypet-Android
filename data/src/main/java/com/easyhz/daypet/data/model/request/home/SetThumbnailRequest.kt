package com.easyhz.daypet.data.model.request.home

import com.google.firebase.Timestamp

data class SetThumbnailRequest(
    val groupId: String,
    val startDate: Timestamp,
    val endDate: Timestamp,
    val monthDate: Timestamp,
    val day: String,
    val url: String
)