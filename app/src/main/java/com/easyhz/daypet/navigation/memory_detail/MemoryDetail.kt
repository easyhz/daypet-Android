package com.easyhz.daypet.navigation.memory_detail

import kotlinx.serialization.Serializable

@Serializable
internal data class MemoryDetail(
    val id: String,
    val title: String
)