package com.easyhz.daypet.home.util

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.easyhz.daypet.design_system.theme.Primary


internal const val CONTENT_SIZE = 36
internal const val NUM_OF_DAYS_IN_WEEK = 7
internal const val PADDING_NUM_OF_DAYS_IN_WEEK = NUM_OF_DAYS_IN_WEEK * 2
internal const val WITHOUT_PADDING_NUM = PADDING_NUM_OF_DAYS_IN_WEEK - 2
internal fun getCalendarPadding(target: Int, screenWidth: Int) =
    (((PADDING_NUM_OF_DAYS_IN_WEEK * target) + (NUM_OF_DAYS_IN_WEEK * CONTENT_SIZE) - screenWidth) / WITHOUT_PADDING_NUM).coerceAtLeast(0)

/**
 * 6자리 16진수 색상 문자열을 [Color] 객체로 변환.
 *
 * 문자열이 정확히 6자리가 아니거나 변환에 실패하면, 오류를 로그에 기록하고 기본 색상 [Primary]를 반환.
 *
 * @receiver 6자리 16진수 색상 문자열 (예: "BFA7F0").
 * @return 변환 성공 -> 해당 [Color] 객체를 반환, 변환 실패 -> 기본 색상 [Primary]를 반환.
 * @throws NumberFormatException 문자열이 6자리 형식에 맞지 않는 경우 or 문자열을 16진수로 변환할 수 없는 경우.
 */
internal fun String.toColor(): Color {
    return try {
        if (this.length != 6) throw NumberFormatException("Does not fit the six-character default format")
        val colorInt = 0xFF000000.toInt() or this.toInt(16)
        Color(colorInt)
    } catch (e: NumberFormatException) {
        Log.e("String.toColor", "Error : ${e.message}")
        Primary
    }
}
