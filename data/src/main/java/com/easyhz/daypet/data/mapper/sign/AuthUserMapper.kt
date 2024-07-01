package com.easyhz.daypet.data.mapper.sign

import com.easyhz.daypet.domain.model.sign.AuthUser
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toModel(): AuthUser = AuthUser(
    uid = this.uid
)