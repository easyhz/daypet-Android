package com.easyhz.daypet.data.model.request.home

import com.google.firebase.Timestamp

data class ThumbnailRequest(
    val groupId: String,
    val startDate: Timestamp,
    val endDate: Timestamp,
)
