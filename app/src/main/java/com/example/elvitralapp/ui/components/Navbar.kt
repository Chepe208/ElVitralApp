package com.example.elvitralapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elvitralapp.ui.theme.DarkBackground
import com.example.elvitralapp.ui.theme.TextPrimary

@Composable
fun Navbar(navController: NavController? = null) {
    var showMenu by remember { mutableStateOf(false) }

    Surface(
        color = DarkBackground.copy(alpha = 0.95f),
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Text(
                text = "EL VITRAL",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            // Navigation Items
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Desktop-like links (visible if space allows, here simplified)
                val navItems = listOf("Inicio", "Catálogo") 
                navItems.forEach { item ->
                    Text(
                        text = item,
                        color = TextPrimary,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                
                Box {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = TextPrimary,
                            modifier = Modifier.size(29.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Login") },
                            onClick = {
                                showMenu = false
                                navController?.navigate("login")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Register") },
                            onClick = {
                                showMenu = false
                                navController?.navigate("register")
                            }
                        )
                    }
                }
            }
        }
    }
}
