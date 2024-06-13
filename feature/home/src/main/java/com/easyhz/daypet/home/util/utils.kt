package com.easyhz.daypet.home.util


internal const val CONTENT_SIZE = 36
internal const val NUM_OF_DAYS_IN_WEEK = 7
internal const val PADDING_NUM_OF_DAYS_IN_WEEK = NUM_OF_DAYS_IN_WEEK * 2
internal const val WITHOUT_PADDING_NUM = PADDING_NUM_OF_DAYS_IN_WEEK - 2
internal fun getCalendarPadding(target: Int, screenWidth: Int) =
    (((PADDING_NUM_OF_DAYS_IN_WEEK * target) + (NUM_OF_DAYS_IN_WEEK * CONTENT_SIZE) - screenWidth) / WITHOUT_PADDING_NUM).coerceAtLeast(0)