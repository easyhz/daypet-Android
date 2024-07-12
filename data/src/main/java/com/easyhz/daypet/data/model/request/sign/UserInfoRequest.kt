package com.easyhz.daypet.data.model.request.sign

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class UserInfoRequest(
    @PropertyName("name")
    val name: String,
    @PropertyName("commentCount")
    val commentCount: Int,
    @PropertyName("creationTime")
    val creationTime: Timestamp,
    @set:PropertyName("groupID")
    @get:PropertyName("groupID")
    var groupId: String,
    @set:PropertyName("thumbnailURL")
    @get:PropertyName("thumbnailURL")
    var thumbnailUrl: String,
    @PropertyName("uploadedMemoryCount")
    val uploadedMemoryCount: Int,
    @PropertyName("visitCount")
    val visitCount: Int
)
