package com.easyhz.daypet.database.datasource.member

import androidx.room.Transaction
import com.easyhz.daypet.database.dao.member.GroupDao
import com.easyhz.daypet.database.dao.member.GroupUserDao
import com.easyhz.daypet.database.dao.member.PetDao
import com.easyhz.daypet.database.di.IoDispatcher
import com.easyhz.daypet.database.entity.member.GroupEntity
import com.easyhz.daypet.database.entity.member.GroupUserEntity
import com.easyhz.daypet.database.entity.member.PetEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GroupMemberDatabaseDataSourceImpl @Inject constructor(
    private val groupDao: GroupDao,
    private val groupUserDao: GroupUserDao,
    private val petDao: PetDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): GroupMemberDatabaseDataSource {
    override suspend fun getGroupMembers() {

    }

    @Transaction
    override suspend fun updateGroupMembers(
        group: GroupEntity,
        groupUsers: List<GroupUserEntity>,
        pets: List<PetEntity>
    ) {
        withContext(dispatcher) {
            runCatching {
                groupDao.deleteAll()
                groupUserDao.deleteAll()
                petDao.deleteAll()

                groupDao.insertGroup(group)
                groupUserDao.insertGroupUsers(groupUsers)
                petDao.insertPets(pets)
            }.onFailure { e ->
                // TODO: 예외 처리
                println(">> 안됨 $e")
            }
        }
    }
}