package com.easyhz.daypet.navigation.memory_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.easyhz.daypet.memory_detail.MemoryDetailScreen
import com.easyhz.daypet.memory_detail.screen.comment.CommentScreen

fun NavGraphBuilder.memoryDetailScreen(
    navigateToUp: () -> Unit,
    navigateToComment: (String, String, String) -> Unit
) {
    composable<MemoryDetail> { navBackStackEntry ->
        val args = navBackStackEntry.toRoute<MemoryDetail>()
        MemoryDetailScreen(
            id = args.id,
            title = args.title,
            navigateToUp = navigateToUp,
            navigateToComment = navigateToComment
        )
    }

    composable<Comment> {
        val args = it.toRoute<Comment>()
        CommentScreen(
            memoryId = args.memoryId,
            memoryTitle = args.memoryTitle,
            thumbnailUrl = args.thumbnailUrl,
            navigateToUp = navigateToUp
        )
    }
}

internal fun NavController.navigateToComment(id: String, title: String, url: String) {
    if (id.isBlank()) navigateUp()
    navigate(Comment(id, title, url))
}
