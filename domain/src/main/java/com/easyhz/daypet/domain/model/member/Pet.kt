package com.easyhz.daypet.domain.model.member

import java.time.LocalDate

data class Pet(
    val id: String,
    val birthTime: LocalDate,
    val breed: String,
    val name: String,
    val gender: String,
    val attributes: List<String>,
    val thumbnailUrl: String,
    val memo: String,
)
