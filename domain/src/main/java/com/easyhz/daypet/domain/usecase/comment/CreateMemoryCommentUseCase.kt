package com.easyhz.daypet.domain.usecase.comment

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.comment.Comment
import com.easyhz.daypet.domain.repository.comment.CommentRepository
import javax.inject.Inject

class CreateMemoryCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository
): BaseUseCase<Comment, Unit>() {
    override suspend fun invoke(param: Comment): Result<Unit> =
        commentRepository.createMemoryComment(param)
}