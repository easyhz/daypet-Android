package com.easyhz.daypet.home.dummy

import com.easyhz.daypet.domain.model.event.Archive
import com.easyhz.daypet.domain.model.event.Task

val ARCHIVE_DUMMY = listOf(
    Archive(title = "산책 갔엉", "https://picsum.photos/id/237/200/300", "10:07")
)

val TASK_DUMMY = listOf(
    Task(title = "목욕 하기", isDone = false),
    Task(title = "산책하기", isDone = true),
)