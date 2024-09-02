package com.easyhz.daypet.domain.repository.comment

import com.easyhz.daypet.domain.model.comment.Comment

interface CommentRepository {
    suspend fun fetchMemoryCommentList(id: String): Result<List<Comment>>
    suspend fun createMemoryComment(data: Comment): Result<Unit>
}