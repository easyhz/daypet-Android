package com.easyhz.daypet.common.error

sealed class DayPetError: Exception() {
    /* 예상하지 못한 에러 */
    data object UnexpectedError : DayPetError() {
        @JvmStatic
        private fun readResolve(): Any = UnexpectedError
    }

    /* 잘못된 형식으로 결과값 반환 X */
    data object NoResultError : DayPetError() {
        @JvmStatic
        private fun readResolve(): Any = NoResultError
    }

}