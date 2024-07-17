package com.easyhz.daypet.navigation.home

import kotlinx.serialization.Serializable

@Serializable
internal data class Home(
    val groupId: String,
    val userId: String
)
