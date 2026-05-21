package com.example.elvitralapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.elvitralapp.ui.LandingPage
import com.example.elvitralapp.ui.theme.ElVitralAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElVitralAppTheme {
                LandingPage()
            }
        }
    }
}
