package com.easyhz.daypet.data.datasource.sign

import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>
}