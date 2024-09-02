package com.easyhz.daypet.navigation.memory_detail

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val memoryId: String,
    val memoryTitle: String,
    val thumbnailUrl: String
)
