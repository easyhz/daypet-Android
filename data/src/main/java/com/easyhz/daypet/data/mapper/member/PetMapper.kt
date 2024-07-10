package com.easyhz.daypet.data.mapper.member

import com.easyhz.daypet.common.extension.toLocalDate
import com.easyhz.daypet.common.extension.toTimeStamp
import com.easyhz.daypet.data.model.request.member.PetInsert
import com.easyhz.daypet.data.model.request.member.PetInsertRequest
import com.easyhz.daypet.data.model.response.member.PetResponse
import com.easyhz.daypet.database.entity.member.PetEntity
import com.easyhz.daypet.domain.model.member.Pet
import com.easyhz.daypet.domain.param.member.PetInsertParam

fun PetResponse.toModel(): Pet = Pet(
    birthTime = this.birthTime.toLocalDate(),
    breed = this.breed,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl,
    memo = this.memo,
    attributes = this.attributes,
    gender = this.gender
)

fun PetResponse.toEntity(id: Int): PetEntity = PetEntity(
    id = id,
    birthTime = this.birthTime.toLocalDate(),
    breed = this.breed,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl,
    memo = this.memo
)

fun PetInsertParam.toRequest(): PetInsertRequest = PetInsertRequest(
    groupId = this.groupId,
    petList = this.petList.map { it.toRequest() },
)

fun Pet.toRequest(): PetInsert = PetInsert(
    birthTime = this.birthTime.toTimeStamp(),
    breed = this.breed,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl,
    memo = this.memo,
    attributes = this.attributes,
    gender = this.gender
)