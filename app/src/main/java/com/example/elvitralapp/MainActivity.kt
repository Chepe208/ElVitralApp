package com.example.elvitralapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elvitralapp.Screens.LoginScreen
import com.example.elvitralapp.Screens.RegisterScreen
import com.example.elvitralapp.Screens.TechnicalVisitScreen
import com.example.elvitralapp.ui.LandingPage
import com.example.elvitralapp.ui.theme.ElVitralAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElVitralAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "landing") {
                        composable("landing") {
                            LandingPage(navController)
                        }
                        composable("login") {
                            LoginScreen(onBack = { navController.popBackStack() })
                        }
                        composable("register") {
                            RegisterScreen(navController = navController, onBack = { navController.popBackStack() })
                        }
                        composable("visit") {
                            TechnicalVisitScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
