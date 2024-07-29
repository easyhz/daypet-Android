package com.easyhz.daypet.data.model.request.home

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class CreateThumbnail(
    @set:PropertyName("groupID")
    @get:PropertyName("groupID")
    var groupId: String,
    val monthDate: Timestamp,
    @set:PropertyName("thumbnailURLDict")
    @get:PropertyName("thumbnailURLDict")
    var thumbnailUrlDict: Map<String, String>
)
