package com.easyhz.daypet.navigation.sign

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.daypet.navigation.sign.screen.Group
import com.easyhz.daypet.navigation.sign.screen.Login
import com.easyhz.daypet.navigation.sign.screen.Profile
import com.easyhz.daypet.sign.LoginScreen
import com.easyhz.daypet.sign.view.group.GroupScreen
import com.easyhz.daypet.sign.view.profile.ProfileScreen

internal fun NavGraphBuilder.signScreen(
    navigateToProfile: () -> Unit,
    navigateToGroup: () -> Unit,
    navigateToBack: () -> Unit
) {
    composable<Login> {
        LoginScreen(
            navigateToProfile = navigateToProfile
        )
    }
    composable<Profile> {
        ProfileScreen(
            navigateToGroup = navigateToGroup,
            navigateToBack = navigateToBack
        )
    }
    composable<Group> {
        GroupScreen(
            navigateToBack = navigateToBack
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