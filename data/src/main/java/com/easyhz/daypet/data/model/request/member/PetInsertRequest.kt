package com.easyhz.daypet.data.model.request.member

import com.google.firebase.Timestamp


data class PetInsertRequest(
    val groupId: String,
    val petList: List<PetInsert>
)

data class PetInsert(
    val birthTime: Timestamp,
    val breed: String,
    val name: String,
    val attributes: List<String>,
    val thumbnailUrl: String,
    val memo: String,
    val gender: String
)
