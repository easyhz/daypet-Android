package com.easyhz.daypet.common.extension

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Timestamp.toLocalDate(): LocalDate {
    val date: Date = this.toDate()
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

/**
 * 확장 함수: LocalDate 를 TimeStamp 로 변환
 *
 * @param plusDays 숫자만큼 날짜 더함 (ex. [plusDays] = 1 -> 2024-06-18 => 2024년 6월 19일 00:00시
 *
 * @return [Timestamp]
 */
fun LocalDate.toTimeStamp(plusDays: Long = 0): Timestamp {
    val localDateTime = this.plusDays(plusDays).atStartOfDay()
    val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
    return Timestamp(instant.epochSecond, instant.nano)
}


/**
 *
 * 확장 함수: Timestamp를 [pattern] 형식의 문자열로 변환
 *
 * @param pattern (ex. "HH:mm"
 */
fun Timestamp.toFormattedTime(pattern: String = "HH:mm"): String {
    val date = this.toDate()
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getDefault() // 사용자의 로컬 시간대로 설정
    return dateFormat.format(date)
}