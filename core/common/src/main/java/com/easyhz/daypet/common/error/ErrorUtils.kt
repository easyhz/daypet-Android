package com.easyhz.daypet.common.error

import androidx.annotation.StringRes
import com.easyhz.daypet.common.R

@StringRes
fun Throwable.getMessageStringRes(): Int =
    when (this) {
        DayPetError.UnexpectedError -> R.string.unexpected_error
        DayPetError.NoResultError -> R.string.no_result_error
        FireStoreError.NotFoundError -> R.string.firestore_not_found_error
        FireStoreError.PermissionDeniedError -> R.string.firestore_permission_denied_error
        else -> R.string.unexpected_error
    }