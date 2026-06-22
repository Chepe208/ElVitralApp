package com.example.elvitralapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.elvitralapp.ui.theme.LocalOnCycleTheme
import com.example.elvitralapp.ui.theme.LocalThemeMode
import com.example.elvitralapp.ui.theme.ThemeMode

@Composable
fun Navbar(navController: NavController? = null) {

    val themeMode    = LocalThemeMode.current
    val onCycleTheme = LocalOnCycleTheme.current

    var showMenu by remember { mutableStateOf(false) }

    val themeIcon = when (themeMode) {
        ThemeMode.DARK         -> Icons.Default.DarkMode
        ThemeMode.LIGHT        -> Icons.Default.LightMode
        ThemeMode.DARK_MEDIUM  -> Icons.Default.Contrast
        ThemeMode.LIGHT_MEDIUM -> Icons.Default.Contrast
        ThemeMode.DARK_HIGH    -> Icons.Default.Contrast
        ThemeMode.LIGHT_HIGH   -> Icons.Default.Contrast
    }
    val themeLabel = when (themeMode) {
        ThemeMode.DARK         -> "Oscuro → Claro"
        ThemeMode.LIGHT        -> "Claro → Oscuro MC"
        ThemeMode.DARK_MEDIUM  -> "Oscuro MC → Claro MC"
        ThemeMode.LIGHT_MEDIUM -> "Claro MC → Oscuro AC"
        ThemeMode.DARK_HIGH    -> "Oscuro AC → Claro AC"
        ThemeMode.LIGHT_HIGH   -> "Claro AC → Oscuro"
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "EL VITRAL",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Inicio",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { navController?.navigate("landing") }
                )

                Text(
                    text = "Catálogo",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { navController?.navigate("catalog") }
                )

                IconButton(onClick = onCycleTheme) {
                    Icon(
                        imageVector = themeIcon,
                        contentDescription = themeLabel,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Box {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Login") },
                            onClick = { showMenu = false; navController?.navigate("login") }
                        )
                        DropdownMenuItem(
                            text = { Text("Register") },
                            onClick = { showMenu = false; navController?.navigate("register") }
                        )
                        DropdownMenuItem(
                            text = { Text("Gestión Catálogo") },
                            onClick = { showMenu = false; navController?.navigate("catalog") }
                        )
                        DropdownMenuItem(
                            text = { Text("Gestión Visitas") },
                            onClick = { showMenu = false; navController?.navigate("visit_manager") }
                        )
                    }
                }
            }
        }
    }
}