package com.easyhz.daypet.data.datasource.comment

import com.easyhz.daypet.data.model.response.comment.CommentResponse
import com.easyhz.daypet.data.util.Collections.COMMENTS
import com.easyhz.daypet.data.util.DocumentWithId
import com.easyhz.daypet.data.util.Fields.CREATION_TIME
import com.easyhz.daypet.data.util.Fields.MEMORY_ID
import com.easyhz.daypet.data.util.collectionWithIdHandler
import com.easyhz.daypet.data.util.setHandler
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.UUID
import javax.inject.Inject

class CommentDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): CommentDataSource {
    override suspend fun fetchMemoryCommentList(id: String): Result<List<DocumentWithId<CommentResponse>>> = collectionWithIdHandler {
        firestore.collection(COMMENTS)
            .whereEqualTo(MEMORY_ID, id)
            .orderBy(CREATION_TIME, Query.Direction.ASCENDING)
            .get()
    }

    override suspend fun createMemoryComment(comment: CommentResponse): Result<Unit> = setHandler {
        firestore.collection(COMMENTS).document(UUID.randomUUID().toString().uppercase()).set(comment)
    }
}