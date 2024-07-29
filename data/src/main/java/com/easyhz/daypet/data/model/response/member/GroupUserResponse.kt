package com.easyhz.daypet.data.model.response.member

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class GroupUserResponse(
    @PropertyName("userID")
    val userId: String = "",
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @PropertyName("joinDate")
    val joinDate: Timestamp = Timestamp.now(),
    @PropertyName("name")
    val name: String = "",
    @PropertyName("groupID")
    val groupId: String = "",
    @PropertyName("thumbnailURL")
    val thumbnailUrl: String = "",
    @PropertyName("fcmToken")
    val fcmToken: String = "",
    @PropertyName("visitCount")
    val visitCount: Int = 1,
    @PropertyName("uploadedMemoryCount")
    val uploadedMemoryCount: Int = 0,
    @PropertyName("commentCount")
    val commentCount: Int = 0,
)