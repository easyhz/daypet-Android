package com.easyhz.daypet.domain.manager

import com.easyhz.daypet.domain.model.member.GroupMember

object UserManager {
    var userId: String? = null
        private set
    var groupId: String? = null
        private set
    var groupInfo: GroupMember? = null
        private set

    fun setUserId(userId: String) {
        this.userId = userId
    }
    fun setGroupId(groupId: String) {
        this.groupId = groupId
    }

    fun setGroupInfo(groupInfo: GroupMember) {
        this.groupInfo = groupInfo
    }

    fun setUserInfo(userId: String, groupId: String, groupInfo: GroupMember) {
        this.userId = userId
        this.groupId = groupId
        this.groupInfo = groupInfo
    }

    fun printInfo() {
        println("=======\n" +
                "userId : ${this.userId}\n" +
                "groupId : ${this.groupId}\n" +
                "groupInfo : ${this.groupInfo}\n" +
                "=======")
    }
}