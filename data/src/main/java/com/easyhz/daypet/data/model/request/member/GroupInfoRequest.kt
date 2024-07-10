package com.easyhz.daypet.data.model.request.member

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class GroupInfoRequest(
    @PropertyName("name")
    val name: String,
    @PropertyName("creationTime")
    val creationTime: Timestamp,
    @get:PropertyName("ownerID")
    @set:PropertyName("ownerID")
    var ownerId: String,
)
