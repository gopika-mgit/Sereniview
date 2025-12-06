package com.example.sereniview.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Breathing : Screen("breathing")
    // later: CalmChat, InterviewChat, GroundMe, History
}