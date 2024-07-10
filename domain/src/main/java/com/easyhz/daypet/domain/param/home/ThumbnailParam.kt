package com.easyhz.daypet.domain.param.home

import java.time.LocalDate

data class ThumbnailParam(
    val groupId: String,
    val startDate: LocalDate,
    val endDate: LocalDate
)
