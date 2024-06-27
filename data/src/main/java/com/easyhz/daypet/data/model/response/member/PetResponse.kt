package com.easyhz.daypet.data.model.response.member

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class PetResponse(
    @PropertyName("birthTime")
    val birthTime: Timestamp = Timestamp.now(),
    @PropertyName("breed")
    val breed: String = "",
    @PropertyName("name")
    val name: String = "",
    @PropertyName("thumbnailURL")
    val thumbnailUrl: String = "",
    @PropertyName("memo")
    val memo: String = ""
)
