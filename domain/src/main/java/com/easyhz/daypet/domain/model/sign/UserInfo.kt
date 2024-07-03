package com.easyhz.daypet.domain.model.sign

data class UserInfo(
    val name: String,
    val commentCount: Int,
    val groupId: String,
    val uploadedMemoryCount: Int,
    val visitCount: Int,
)

sealed class LoginStep {
    /* 새로운 유저 */
    data object NewUser: LoginStep()

    /* 프로필 있지만 그룹은 없을 때 */
    data class NoGroup(val name: String): LoginStep()

    /* 전부 설정 완료 */
    data object ExistUser: LoginStep()
}
