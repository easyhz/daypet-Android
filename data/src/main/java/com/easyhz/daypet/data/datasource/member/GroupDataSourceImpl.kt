package com.easyhz.daypet.data.datasource.member

import com.easyhz.daypet.data.model.request.member.GroupRequest
import com.easyhz.daypet.data.model.response.member.GroupResponse
import com.easyhz.daypet.data.util.Collections.GROUPS
import com.easyhz.daypet.data.util.documentHandler
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): GroupDataSource {
    override suspend fun fetchGroupInfo(data: GroupRequest): Result<GroupResponse> = documentHandler {
        firestore.collection(GROUPS)
            .document(data.groupId)
            .get()
    }
}