package com.angad.collegeapp.navigation

sealed class Routes(val route: String) {
//    Creating route of all screen
    object Home: Routes("home")
    object Faculty: Routes("faculty")
    object Gallery: Routes("gallery")
    object AboutUs: Routes("about_us")
    object BottomNav: Routes("bottom_nav")

}