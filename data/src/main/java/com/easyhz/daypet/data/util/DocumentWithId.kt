package com.easyhz.daypet.data.util

data class DocumentWithId<T>(
    val id: String,
    val data: T
)
