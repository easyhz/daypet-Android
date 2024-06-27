package com.easyhz.daypet.domain.model.member

import java.time.LocalDate

data class Pet(
    val birthTime: LocalDate,
    val breed: String,
    val name: String,
    val thumbnailUrl: String,
    val memo: String
)
