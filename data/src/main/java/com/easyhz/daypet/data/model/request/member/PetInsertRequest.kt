package com.easyhz.daypet.data.model.request.member

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName


data class PetInsertRequest(
    val groupId: String,
    val petList: List<PetInsert>
)

data class PetInsert(
    val id: String,
    val birthTime: Timestamp,
    val breed: String,
    val name: String,
    val attributes: List<String>,
    @set:PropertyName("thumbnailURL")
    @get:PropertyName("thumbnailURL")
    var thumbnailUrl: String,
    val memo: String,
    val gender: String
)
