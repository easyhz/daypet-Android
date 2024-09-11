package com.easyhz.daypet.data.model.request.todo

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class CreateTodoRequest(
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @get:PropertyName("groupID")
    @set:PropertyName("groupID")
    var groupId: String = "",
    @PropertyName("title")
    val title: String = "",
    @get:PropertyName("isDone")
    @set:PropertyName("isDone")
    var isDone: Boolean = false,
    @PropertyName("todoColor")
    val todoColor: String = "",
    @PropertyName("todoDate")
    val todoDate: Timestamp = Timestamp.now(),
    @get:PropertyName("uploaderID")
    @set:PropertyName("uploaderID")
    var uploaderId: String = ""
)
