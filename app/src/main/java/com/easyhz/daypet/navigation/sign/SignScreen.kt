package com.easyhz.daypet.navigation.sign

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.daypet.navigation.sign.screen.Group
import com.easyhz.daypet.navigation.sign.screen.Login
import com.easyhz.daypet.navigation.sign.screen.Profile
import com.easyhz.daypet.sign.AuthViewModel
import com.easyhz.daypet.sign.LoginScreen
import com.easyhz.daypet.sign.view.group.GroupScreen
import com.easyhz.daypet.sign.view.profile.ProfileScreen

internal fun NavGraphBuilder.signScreen(
    navController: NavController,
) {
    composable<Login> {
        val viewModel: AuthViewModel = hiltViewModel()
        LoginScreen(
            viewModel = viewModel,
            navigateToProfile = navController::navigateToProfile,
            navigateToGroup = navController::navigateToGroup
        )
    }
    composable<Profile> {navBackStackEntry ->
        val parentEntry = remember(navBackStackEntry) {
            navController.getBackStackEntry(Login)
        }
        ProfileScreen(
            viewModel = hiltViewModel(parentEntry),
            navigateToGroup = navController::navigateToGroup,
            navigateToBack = navController::navigateUp
        )
    }
    composable<Group> {
        GroupScreen(
            navigateToBack = navController::navigateUp
        )
    }
}

internal fun NavController.navigateToProfile() {
    navigate(Profile)
}

internal fun NavController.navigateToGroup() {
    navigate(Group) {
        popUpTo(this@navigateToGroup.graph.id) { inclusive = true }
    }
}