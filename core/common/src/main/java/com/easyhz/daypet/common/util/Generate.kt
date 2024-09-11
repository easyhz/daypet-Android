package com.easyhz.daypet.common.util

import java.util.UUID

object Generate {
    fun randomUUID() = UUID.randomUUID().toString().uppercase()
}