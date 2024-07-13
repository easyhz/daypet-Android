package com.easyhz.daypet.data.model.response.member

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class PetResponse(
    @PropertyName("id")
    val id: String = "",
    @PropertyName("birthTime")
    val birthTime: Timestamp = Timestamp.now(),
    @PropertyName("breed")
    val breed: String = "",
    @PropertyName("name")
    val name: String = "",
    @PropertyName("attributes")
    val attributes: List<String> = emptyList(),
    @PropertyName("gender")
    val gender: String = "",
    @PropertyName("thumbnailURL")
    val thumbnailUrl: String = "",
    @PropertyName("memo")
    val memo: String = ""
)
