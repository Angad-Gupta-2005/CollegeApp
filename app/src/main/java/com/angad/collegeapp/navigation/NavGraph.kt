package com.angad.collegeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angad.collegeapp.admin.screens.AdminDashboard
import com.angad.collegeapp.admin.screens.ManageBanner
import com.angad.collegeapp.admin.screens.ManageCollegeInfo
import com.angad.collegeapp.admin.screens.ManageFaculty
import com.angad.collegeapp.admin.screens.ManageGallery
import com.angad.collegeapp.screens.AboutUs
import com.angad.collegeapp.screens.BottomNav
import com.angad.collegeapp.screens.Faculty
import com.angad.collegeapp.screens.Gallery
import com.angad.collegeapp.screens.Home

@Composable
fun NavGraph(modifier: Modifier = Modifier, navController: NavHostController) {

    val isAdmin = true

    NavHost(
        navController = navController,
        startDestination = if (isAdmin)  Routes.AdminDashboard.route else Routes.BottomNav.route
    ) {

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
//    For admin screens
        composable(Routes.AdminDashboard.route) {
            AdminDashboard( navController )
        }

        composable(Routes.ManageBanner.route) {
            ManageBanner()
        }

        composable(Routes.ManageFaculty.route) {
            ManageFaculty()
        }

        composable(Routes.ManageGallery.route) {
            ManageGallery()
        }

        composable(Routes.ManageCollegeInfo.route) {
            ManageCollegeInfo( )
        }
    }
}