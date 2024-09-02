package com.easyhz.daypet.data.datasource.comment

import com.easyhz.daypet.data.model.response.comment.CommentResponse
import com.easyhz.daypet.data.util.DocumentWithId

interface CommentDataSource {
    suspend fun fetchMemoryCommentList(id: String): Result<List<DocumentWithId<CommentResponse>>>
    suspend fun createMemoryComment(comment: CommentResponse): Result<Unit>
}