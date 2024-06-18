package com.easyhz.daypet.common.error

import android.content.Context
import com.easyhz.daypet.common.R

fun DayPetError.getLocalizedMessage(context: Context): String =
    when (this) {
        is DayPetError.UnexpectedError -> context.getString(R.string.unexpected_error)
        DayPetError.NoResultError -> TODO()
        FireStoreError.NotFoundError -> TODO()
        FireStoreError.PermissionDeniedError -> TODO()
    }