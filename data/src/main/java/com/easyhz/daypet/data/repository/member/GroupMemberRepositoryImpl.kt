package com.easyhz.daypet.data.repository.member

import android.content.Context
import androidx.core.net.toUri
import com.easyhz.daypet.common.error.DayPetError
import com.easyhz.daypet.data.datasource.image.ImageDataSource
import com.easyhz.daypet.data.datasource.member.GroupDataSource
import com.easyhz.daypet.data.datasource.member.GroupUserDataSource
import com.easyhz.daypet.data.di.IoDispatcher
import com.easyhz.daypet.data.mapper.member.GroupMemberMapper
import com.easyhz.daypet.data.mapper.member.toEntity
import com.easyhz.daypet.data.mapper.member.toPairEntity
import com.easyhz.daypet.data.mapper.member.toRequest
import com.easyhz.daypet.data.util.Storage
import com.easyhz.daypet.database.datasource.member.GroupMemberDatabaseDataSource
import com.easyhz.daypet.domain.model.member.GroupMember
import com.easyhz.daypet.domain.param.member.GroupInfoParam
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.param.member.PetInsertParam
import com.easyhz.daypet.domain.repository.member.GroupMemberRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class GroupMemberRepositoryImpl @Inject constructor(
    private val groupDataSource: GroupDataSource,
    private val groupUserDataSource: GroupUserDataSource,
    private val groupMemberDatabaseDatasource: GroupMemberDatabaseDataSource,
    private val imageDataSource: ImageDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GroupMemberRepository {
    override suspend fun fetchGroupMember(param: GroupMemberParam): Result<GroupMember> =
        withContext(dispatcher) {
            runCatching {
                val groupDeferred = async {
                    groupDataSource.fetchGroupInfo(
                        GroupMemberMapper.toGroupRequest(param)
                    )
                }
                val usersDeferred = async {
                    groupUserDataSource.fetchGroupUsers(
                        GroupMemberMapper.toGroupUserRequest(param)
                    )
                }

                val groupResult = groupDeferred.await()
                val userResult = usersDeferred.await()

                if (groupResult.isSuccess && userResult.isSuccess) {
                    val group = groupResult.getOrThrow()
                    val user = userResult.getOrThrow()
                    supervisorScope {
                        launch {
                            val (groupInfo, pets) = group.toPairEntity(param.groupId)
                            val groupUsers = user.mapIndexed { index, groupUserResponse ->
                                groupUserResponse.toEntity(index)
                            }
                            groupMemberDatabaseDatasource.updateGroupMembers(
                                group = groupInfo,
                                groupUsers = groupUsers,
                                pets = pets
                            )
                        }
                        GroupMemberMapper.toModel(group, user)
                    }
                } else {
                    throw DayPetError.UnexpectedError
                }
            }.fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it) },
            )
        }

    override suspend fun createGroup(param: GroupInfoParam): Result<String> =
        groupDataSource.createGroup(param.toRequest())

    override suspend fun insertPetInGroup(param: PetInsertParam): Result<Unit> =
        runCatching {
            val updatedPetList = param.petList.map {
                it.copy(
                    thumbnailUrl = imageDataSource
                        .uploadImage(Storage.PETS, it.thumbnailUrl.toUri(), UUID.randomUUID().toString())
                        .getOrThrow()
                )
            }
            val updatedParam = param.copy(petList = updatedPetList)
            groupDataSource.insertPetInGroup(updatedParam.toRequest()).getOrThrow()
        }.fold(
            onSuccess = { Result.success(Unit) },
            onFailure = { Result.failure(it) }
        )
}
