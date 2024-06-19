package com.easyhz.daypet.data.model.response.memory

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class MemoryResponse(
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @PropertyName("groupID")
    val groupId: String = "",
    @PropertyName("imageURLs")
    val imageUrls: List<String> = emptyList(),
    @PropertyName("title")
    val title: String = "",
    @PropertyName("content")
    val content: String = "",
    @PropertyName("uploaderID")
    val uploaderId: String = "",
    @get:PropertyName("isPublic")
    val isPublic: Boolean = false,
    @PropertyName("thumbnailURL")
    val thumbnailUrl: String = "",
    @PropertyName("memberIDs")
    val memberIds: List<String> = emptyList(),
    @PropertyName("fcmTokens")
    val fcmTokens: List<String> = emptyList(),
)
