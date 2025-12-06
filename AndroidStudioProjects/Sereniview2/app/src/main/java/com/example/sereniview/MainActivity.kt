package com.example.sereniview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.sereniview.ui.components.SereniviewBackground
import com.example.sereniview.ui.navigation.SereniviewNavGraph
import com.example.sereniview.ui.theme.SereniviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SereniviewApp()
        }
    }
}

@Composable
fun SereniviewApp() {
    SereniviewTheme {
        SereniviewBackground {
            val navController = rememberNavController()
            SereniviewNavGraph(navController = navController)
        }
    }
}