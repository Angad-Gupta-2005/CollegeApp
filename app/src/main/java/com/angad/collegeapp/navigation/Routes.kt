package com.angad.collegeapp.navigation

sealed class Routes(val route: String) {
//    Creating route of all screen
    object Home: Routes("home")
    object Faculty: Routes("faculty")
    object Gallery: Routes("gallery")
    object AboutUs: Routes("about_us")
    object BottomNav: Routes("bottom_nav")

//    For admin screen
    object AdminDashboard: Routes("admin_dashboard")
    object ManageBanner: Routes("manage_banner")
    object ManageFaculty: Routes("manage_faculty")
    object ManageGallery: Routes("manage_gallery")
    object ManageCollegeInfo: Routes("college_info")

}