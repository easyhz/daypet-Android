package com.easyhz.daypet.data.mapper.sign

import com.easyhz.daypet.data.model.request.sign.UserInfoRequest
import com.easyhz.daypet.domain.model.sign.AuthUser
import com.easyhz.daypet.domain.param.sign.UserInfoParam
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toModel(): AuthUser = AuthUser(
    uid = this.uid
)

fun UserInfoParam.toRequest(): UserInfoRequest = UserInfoRequest(
    name = this.name,
    commentCount = 0,
    groupId = "",
    uploadedMemoryCount = 0,
    visitCount = 1,
    creationTime = Timestamp.now()
)