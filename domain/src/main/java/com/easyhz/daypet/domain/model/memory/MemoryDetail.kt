package com.easyhz.daypet.domain.model.memory


data class MemoryDetail(
    val documentId: String = "",
    val title: String,
    val content: String,
    val membersId: List<String>,
    val petsId: List<String>,
    val imageUrl: List<String>,
    val thumbnailUrl: String,
    val date: String,
)
