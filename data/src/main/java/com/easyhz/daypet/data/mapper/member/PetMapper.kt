package com.easyhz.daypet.data.mapper.member

import com.easyhz.daypet.common.extension.toLocalDate
import com.easyhz.daypet.data.model.response.member.PetResponse
import com.easyhz.daypet.database.entity.member.PetEntity
import com.easyhz.daypet.domain.model.member.Pet

fun PetResponse.toModel(): Pet = Pet(
    birthTime = this.birthTime.toLocalDate(),
    breed = this.breed,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl,
    memo = this.memo
)

fun PetResponse.toEntity(id: Int): PetEntity = PetEntity(
    id = id,
    birthTime = this.birthTime.toLocalDate(),
    breed = this.breed,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl,
    memo = this.memo
)
