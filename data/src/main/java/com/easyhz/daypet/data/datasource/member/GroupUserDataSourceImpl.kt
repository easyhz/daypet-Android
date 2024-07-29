package com.easyhz.daypet.data.datasource.member

import com.easyhz.daypet.data.model.request.member.GroupUserRequest
import com.easyhz.daypet.data.model.response.member.GroupUserResponse
import com.easyhz.daypet.data.util.Collections.USERS
import com.easyhz.daypet.data.util.Fields.GROUP_ID
import com.easyhz.daypet.data.util.collectionHandler
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GroupUserDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): GroupUserDataSource {
    override suspend fun fetchGroupUsers(data: GroupUserRequest): Result<List<GroupUserResponse>> = collectionHandler {
        firestore.collection(USERS)
            .whereEqualTo(GROUP_ID, data.groupId)
            .get()
    }
}