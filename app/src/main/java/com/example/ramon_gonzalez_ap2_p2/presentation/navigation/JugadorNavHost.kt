package com.example.ramon_gonzalez_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ramon_gonzalez_ap2_p2.presentation.list.JugadorListScreen
import com.example.ramon_gonzalez_ap2_p2.presentation.detail.JugadorDetailScreen

@Composable
fun JugadorNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.JugadorList
    ) {
        composable<Screen.JugadorList> {
            JugadorListScreen(
                onNavigateToDetail = { id -> navController.navigate(Screen.JugadorDetail(id)) },
                onNavigateToCreate = { navController.navigate(Screen.JugadorDetail(0)) }
            )
        }

        composable<Screen.JugadorDetail> {
            JugadorDetailScreen(
                onBack = { navController.navigateUp() }
            )
        }
    }
}