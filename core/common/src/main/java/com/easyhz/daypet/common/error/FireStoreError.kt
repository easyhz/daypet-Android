package com.easyhz.daypet.common.error

import com.google.firebase.firestore.FirebaseFirestoreException.Code

sealed class FireStoreError: DayPetError() {
    data object NotFoundError: DayPetError() {
        @JvmStatic
        private fun readResolve(): Any = NotFoundError
    }

    data object PermissionDeniedError: DayPetError() {
        @JvmStatic
        private fun readResolve(): Any = NotFoundError
    }
}

fun getErrorByCode(code: Code) : DayPetError =
    when(code) {
        Code.NOT_FOUND -> FireStoreError.NotFoundError
        Code.PERMISSION_DENIED -> FireStoreError.PermissionDeniedError
        else -> DayPetError.UnexpectedError
    }