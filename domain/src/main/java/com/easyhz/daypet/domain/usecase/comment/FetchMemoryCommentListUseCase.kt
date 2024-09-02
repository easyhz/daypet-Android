package com.easyhz.daypet.domain.usecase.comment

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.comment.Comment
import com.easyhz.daypet.domain.repository.comment.CommentRepository
import javax.inject.Inject

class FetchMemoryCommentListUseCase @Inject constructor(
    private val commentRepository: CommentRepository
): BaseUseCase<String, List<Comment>>() {
    override suspend fun invoke(param: String): Result<List<Comment>> =
        commentRepository.fetchMemoryCommentList(param)
}