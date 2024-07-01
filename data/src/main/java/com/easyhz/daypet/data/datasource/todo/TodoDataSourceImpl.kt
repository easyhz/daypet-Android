package com.easyhz.daypet.data.datasource.todo

import com.easyhz.daypet.data.model.request.todo.TodoRequest
import com.easyhz.daypet.data.model.response.todo.TodoResponse
import com.easyhz.daypet.data.util.Collections.TODOS
import com.easyhz.daypet.data.util.Fields.CREATION_TIME
import com.easyhz.daypet.data.util.collectionHandler
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import javax.inject.Inject

class TodoDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): TodoDataSource {
    override suspend fun fetchTodosOnDate(data: TodoRequest): Result<List<TodoResponse>> = collectionHandler {
        firestore.collection(TODOS)
            .whereGreaterThan(CREATION_TIME, data.startDate)
            .whereLessThan(CREATION_TIME, data.endDate)
            .orderBy(CREATION_TIME, DESCENDING).get()
    }
}