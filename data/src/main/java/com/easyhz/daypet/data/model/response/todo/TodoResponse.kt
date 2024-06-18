package com.easyhz.daypet.data.model.response.todo

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class TodoResponse(
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @PropertyName("groupId")
    val groupId: String = "",
    @PropertyName("title")
    val title: String = "",
    @get:PropertyName("isDone")
    val isDone: Boolean = false,
    @PropertyName("todoColor")
    val todoColor: String = "",
    @PropertyName("uploaderID")
    val uploaderId: String = ""
)
