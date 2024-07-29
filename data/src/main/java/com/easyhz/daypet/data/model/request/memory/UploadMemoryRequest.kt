package com.easyhz.daypet.data.model.request.memory

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class UploadMemoryRequest(
    @PropertyName("title")
    val title: String,
    @PropertyName("content")
    val content: String,
    @PropertyName("creationTime")
    val creationTime: Timestamp,
    @get:PropertyName("groupID")
    @set:PropertyName("groupID")
    var groupId: String,
    @PropertyName("fcmTokens")
    val fcmTokens: List<String>,
    @set:PropertyName("imageURLs")
    @get:PropertyName("imageURLs")
    var imageUrls: List<String>,
    @set:PropertyName("isPublic")
    @get:PropertyName("isPublic")
    var isPublic: Boolean = false,
    @get:PropertyName("memberIDs")
    @set:PropertyName("memberIDs")
    var members: List<String>,
    @get:PropertyName("petIDs")
    @set:PropertyName("petIDs")
    var pets: List<String>,
    @set:PropertyName("thumbnailURL")
    @get:PropertyName("thumbnailURL")
    var thumbnailUrl: String,
    @set:PropertyName("uploaderID")
    @get:PropertyName("uploaderID")
    var uploaderId: String
)