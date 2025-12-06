package com.example.sereniview.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sereniview.ui.screens.*

@Composable
fun SereniviewNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // ---------- LOGIN ----------
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onGuest = { navController.navigate("home") }
            )
        }

        // ---------- HOME ----------
        composable("home") {
            HomeScreen(
                onCalmChat = { navController.navigate("calm_chat") },
                onGroundMe = { navController.navigate("ground_me") },
                onBreathing = { navController.navigate("breathing") },
                onAffirmations = { navController.navigate("affirm") },
                onTips = { navController.navigate("tips") },
                onWarmup = { navController.navigate("warmup") },
                onHistory = { navController.navigate("ground_history") }
            )
        }

        // ---------- CALM CHAT ----------
        composable("calm_chat") {
            CalmChatScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ---------- GROUND ME ----------
        composable("ground_me") {
            GroundMeScreen(
                onBack = { navController.popBackStack() },
                onViewHistory = { navController.navigate("ground_history") }
            )
        }

        // ---------- GROUND ME HISTORY ----------
        composable("ground_history") {
            GroundHistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ---------- BREATHING ----------
        composable("breathing") {
            BreathingScreen(
                onBackHome = { navController.popBackStack() }
            )
        }

        // ---------- AFFIRMATIONS ----------
        composable("affirm") {
            AffirmationScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ---------- INTERVIEW TIPS ----------
        composable("tips") {
            TipsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ---------- MINI INTERVIEW WARMUP ----------
        composable("warmup") {
            WarmupScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}