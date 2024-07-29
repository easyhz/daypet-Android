package com.easyhz.daypet.data.model.response.sign

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class UserInfoResponse(
    @PropertyName("userID")
    val userId: String = "",
    @PropertyName("name")
    val name: String = "",
    @PropertyName("commentCount")
    val commentCount: Int = 0,
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @PropertyName("groupID")
    val groupId: String = "",
    @PropertyName("uploadedMemoryCount")
    val uploadedMemoryCount: Int = 0,
    @PropertyName("thumbnailURL")
    val thumbnailUrl: String = "",
    @PropertyName("visitCount")
    val visitCount: Int = 1
)
