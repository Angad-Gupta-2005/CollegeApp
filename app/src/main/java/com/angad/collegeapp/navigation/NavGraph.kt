package com.angad.collegeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angad.collegeapp.screens.AboutUs
import com.angad.collegeapp.screens.BottomNav
import com.angad.collegeapp.screens.Faculty
import com.angad.collegeapp.screens.Gallery
import com.angad.collegeapp.screens.Home

@Composable
fun NavGraph(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.BottomNav.route) {

        composable(Routes.BottomNav.route) {
            BottomNav(navController)
        }

        composable(Routes.Home.route) {
            Home( )
        }

        composable(Routes.Gallery.route) {
            Gallery()
        }

        composable(Routes.AboutUs.route) {
            AboutUs()
        }

        composable(Routes.Faculty.route) {
            Faculty()
        }
    }
}