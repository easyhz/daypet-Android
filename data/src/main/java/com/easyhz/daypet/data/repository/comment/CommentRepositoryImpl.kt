package com.easyhz.daypet.data.repository.comment

import com.easyhz.daypet.data.datasource.comment.CommentDataSource
import com.easyhz.daypet.data.mapper.comment.toModel
import com.easyhz.daypet.data.mapper.comment.toRequest
import com.easyhz.daypet.domain.model.comment.Comment
import com.easyhz.daypet.domain.repository.comment.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentDataSource: CommentDataSource
) : CommentRepository {
    override suspend fun fetchMemoryCommentList(id: String): Result<List<Comment>> =
        commentDataSource.fetchMemoryCommentList(id).map { list ->
            list.map { it.data.toModel(it.id) }
        }

    override suspend fun createMemoryComment(data: Comment): Result<Unit> =
        commentDataSource.createMemoryComment(data.toRequest())
}