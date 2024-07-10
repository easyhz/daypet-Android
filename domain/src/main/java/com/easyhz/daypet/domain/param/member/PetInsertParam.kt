package com.easyhz.daypet.domain.param.member

import com.easyhz.daypet.domain.model.member.Pet

data class PetInsertParam(
    val groupId: String,
    val petList: List<Pet>
)