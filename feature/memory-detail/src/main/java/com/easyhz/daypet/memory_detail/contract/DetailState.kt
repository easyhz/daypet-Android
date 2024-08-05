package com.easyhz.daypet.memory_detail.contract

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.model.memory.MemoryDetail

data class DetailState(
    val isLoading: Boolean,
    val memoryState: MemoryState,
): UiState() {
    companion object {
        fun init() = DetailState(
            isLoading = false,
            memoryState = MemoryState(
                documentId = "",
                title = "",
                content = "",
                membersId = emptyList(),
                petsId = emptyList(),
                imageUrl = emptyList(),
                date = ""
            )
        )
    }
}

internal fun MemoryDetail.toMemoryState(): MemoryState {
    val members = filterAndMapMembers(
        items = UserManager.groupInfo?.groupUsers,
        remoteItems = this.membersId,
        idSelector = { it.userId },
        nameSelector = { it.name },
        imageUrlSelector = { it.thumbnailUrl }
    )
    val pets = filterAndMapMembers(
        items = UserManager.groupInfo?.pets,
        remoteItems = this.petsId,
        idSelector = { it.id },
        nameSelector = { it.name },
        imageUrlSelector = { it.thumbnailUrl }
    )
    return MemoryState(
        documentId = this.documentId,
        title = this.title,
        content = this.content,
        membersId = members ?: emptyList(),
        petsId = pets ?: emptyList(),
        imageUrl = this.imageUrl,
        date = this.date,
    )
}

data class MemoryState(
    val documentId: String = "",
    val title: String,
    val content: String,
    val membersId: List<Member>,
    val petsId: List<Member>,
    val imageUrl: List<String>,
    val date: String
)

data class Member(
    val id: String,
    val name: String,
    val imageUrl: String
)
fun <T> MemoryDetail.filterAndMapMembers(
    items: List<T>?,
    remoteItems: List<String>,
    idSelector: (T) -> String,
    nameSelector: (T) -> String,
    imageUrlSelector: (T) -> String
): List<Member>? {
    return items?.filter { item ->
        remoteItems.any { it == idSelector(item) }
    }?.map {
        Member(
            id = idSelector(it),
            name = nameSelector(it),
            imageUrl = imageUrlSelector(it)
        )
    }
}