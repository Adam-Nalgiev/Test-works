package com.mountech.binner.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mountech.binner.presentation.screen.history.History
import com.mountech.binner.presentation.screen.request.Request

@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Request,
        modifier = modifier.fillMaxSize()
    ) {
        composable<Screen.Request> {
            Request(
                { navigateTo -> navHostController.navigate(navigateTo) },
                paddingValues
            )
        }
        composable<Screen.History> {
            History(
                { navigateTo -> navHostController.navigate(navigateTo) },
                paddingValues,
                modifier.fillMaxSize()
            )
        }
    }
}