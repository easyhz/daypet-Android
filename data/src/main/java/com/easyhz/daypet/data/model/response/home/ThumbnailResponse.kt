package com.easyhz.daypet.data.model.response.home

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class ThumbnailResponse(
    @PropertyName("groupID")
    val groupId: String = "",
    @PropertyName("monthDate")
    val monthDate: Timestamp = Timestamp.now(),
    @PropertyName("thumbnailURLDict")
    val thumbnailUrlDict: HashMap<String, String> = hashMapOf()
)
