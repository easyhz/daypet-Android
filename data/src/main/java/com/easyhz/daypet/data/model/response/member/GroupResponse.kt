package com.easyhz.daypet.data.model.response.member

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class GroupResponse(
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @PropertyName("name")
    val name: String = "",
    @PropertyName("ownerID")
    val ownerId: String = "",
    @PropertyName("pets")
    val pets: List<PetResponse> = emptyList()
)
