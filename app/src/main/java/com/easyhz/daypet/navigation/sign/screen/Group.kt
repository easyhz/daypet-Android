package com.easyhz.daypet.navigation.sign.screen

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val name: String,
    val ownerId: String
)