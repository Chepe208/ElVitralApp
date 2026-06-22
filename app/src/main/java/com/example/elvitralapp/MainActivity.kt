package com.example.elvitralapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elvitralapp.Screens.CatalogoScreen
import com.example.elvitralapp.Screens.LoginScreen
import com.example.elvitralapp.Screens.RegisterScreen
import com.example.elvitralapp.Screens.TechnicalVisitScreen
import com.example.elvitralapp.Screens.VisitManagerScreen
import com.example.elvitralapp.ui.LandingPage
import com.example.elvitralapp.ui.theme.ElVitralAppTheme
import com.example.elvitralapp.ui.theme.ThemeMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var themeMode by remember { mutableStateOf(ThemeMode.DARK) }

            ElVitralAppTheme(
                themeMode = themeMode,
                onCycleTheme = {
                    themeMode = when (themeMode) {
                        ThemeMode.DARK         -> ThemeMode.LIGHT
                        ThemeMode.LIGHT        -> ThemeMode.DARK_MEDIUM
                        ThemeMode.DARK_MEDIUM  -> ThemeMode.LIGHT_MEDIUM
                        ThemeMode.LIGHT_MEDIUM -> ThemeMode.DARK_HIGH
                        ThemeMode.DARK_HIGH    -> ThemeMode.LIGHT_HIGH
                        ThemeMode.LIGHT_HIGH   -> ThemeMode.DARK
                    }
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "landing") {

                        composable("landing") {
                            LandingPage(navController = navController)
                        }
                        composable("login") {
                            LoginScreen(onBack = { navController.popBackStack() })
                        }
                        composable("register") {
                            RegisterScreen(
                                navController = navController,
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable("visit") {
                            TechnicalVisitScreen(onBack = { navController.popBackStack() })
                        }
                        composable("catalog") {
                            CatalogoScreen(onBack = { navController.popBackStack() })
                        }
                        composable("visit_manager") {
                            VisitManagerScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}