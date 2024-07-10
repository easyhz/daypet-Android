package com.easyhz.daypet.domain.usecase.member

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.param.member.PetInsertParam
import com.easyhz.daypet.domain.repository.member.GroupMemberRepository
import javax.inject.Inject

class InsertPetInGroupUseCase @Inject constructor(
    private val groupMemberRepository: GroupMemberRepository
): BaseUseCase<PetInsertParam, Unit>() {
    override suspend fun invoke(param: PetInsertParam): Result<Unit> =
        groupMemberRepository.insertPetInGroup(param)
}