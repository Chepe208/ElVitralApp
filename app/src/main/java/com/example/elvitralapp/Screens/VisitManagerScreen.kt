package com.example.elvitralapp.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elvitralapp.ui.theme.LocalOnCycleTheme
import com.example.elvitralapp.ui.theme.LocalThemeMode
import com.example.elvitralapp.ui.theme.ThemeMode

data class TechnicalVisit(
    val id: String, val clientName: String, val phone: String,
    val address: String, val status: String, val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitManagerScreen(onBack: () -> Unit) {
    val themeMode    = LocalThemeMode.current
    val onCycleTheme = LocalOnCycleTheme.current
    val themeIcon = when (themeMode) {
        ThemeMode.DARK         -> Icons.Default.DarkMode
        ThemeMode.LIGHT        -> Icons.Default.LightMode
        ThemeMode.DARK_MEDIUM,
        ThemeMode.LIGHT_MEDIUM,
        ThemeMode.DARK_HIGH,
        ThemeMode.LIGHT_HIGH   -> Icons.Default.Contrast
    }

    val visits = remember {
        mutableStateListOf(
            TechnicalVisit("1", "Juan Pérez",   "555-0123", "Av. Reforma 123",           "Pendiente",  "2023-10-25"),
            TechnicalVisit("2", "María García", "555-4567", "Calle Juárez 45",           "En Proceso", "2023-10-26"),
            TechnicalVisit("3", "Carlos López", "555-8901", "Blvd. Independencia 789",   "Completada", "2023-10-24")
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Gestor de Visitas",
                    color = MaterialTheme.colorScheme.onSurface) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver",
                            tint = MaterialTheme.colorScheme.onSurface)
                    }
                },
                actions = {
                    IconButton(onClick = onCycleTheme) {
                        Icon(themeIcon, "Cambiar tema",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Administración de Visitas Técnicas",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(visits) { visit ->
                    VisitItem(visit, onStatusChange = { newStatus ->
                        val index = visits.indexOfFirst { it.id == visit.id }
                        if (index != -1) visits[index] = visit.copy(status = newStatus)
                    })
                }
            }
        }
    }
}

@Composable
fun VisitItem(visit: TechnicalVisit, onStatusChange: (String) -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(visit.clientName,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
                StatusBadge(visit.status)
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoRow(Icons.Default.Phone,      visit.phone)
            InfoRow(Icons.Default.LocationOn, visit.address)
            InfoRow(Icons.Default.DateRange,  visit.date)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Box {
                    Button(
                        onClick = { showMenu = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text("Cambiar Estado",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 12.sp)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        listOf("Pendiente", "En Proceso", "Completada", "Cancelada").forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status) },
                                onClick = { onStatusChange(status); showMenu = false }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)) {
        Icon(icon, null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
    }
}

@Composable
fun StatusBadge(status: String) {
    val backgroundColor = when (status) {
        "Pendiente"  -> MaterialTheme.colorScheme.tertiaryContainer
        "En Proceso" -> MaterialTheme.colorScheme.primaryContainer
        "Completada" -> MaterialTheme.colorScheme.secondaryContainer
        else         -> MaterialTheme.colorScheme.errorContainer
    }
    val textColor = when (status) {
        "Pendiente"  -> MaterialTheme.colorScheme.onTertiaryContainer
        "En Proceso" -> MaterialTheme.colorScheme.onPrimaryContainer
        "Completada" -> MaterialTheme.colorScheme.onSecondaryContainer
        else         -> MaterialTheme.colorScheme.onErrorContainer
    }
    Surface(color = backgroundColor, shape = RoundedCornerShape(16.dp)) {
        Text(status, color = textColor, fontSize = 11.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    }
}