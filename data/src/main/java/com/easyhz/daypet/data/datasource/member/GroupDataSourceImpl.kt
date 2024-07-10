package com.easyhz.daypet.data.datasource.member

import com.easyhz.daypet.data.model.request.member.GroupInfoRequest
import com.easyhz.daypet.data.model.request.member.GroupRequest
import com.easyhz.daypet.data.model.request.member.PetInsertRequest
import com.easyhz.daypet.data.model.response.member.GroupResponse
import com.easyhz.daypet.data.util.Collections.GROUPS
import com.easyhz.daypet.data.util.Fields.PETS
import com.easyhz.daypet.data.util.addAndGetIdHandler
import com.easyhz.daypet.data.util.documentHandler
import com.easyhz.daypet.data.util.setHandler
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

    override suspend fun createGroup(data: GroupInfoRequest): Result<String>  = addAndGetIdHandler {
        firestore.collection(GROUPS).add(data)
    }

    override suspend fun insertPetInGroup(data: PetInsertRequest): Result<Unit> = setHandler {
        firestore.collection(GROUPS)
            .document(data.groupId)
            .update(PETS, data.petList)
    }
}