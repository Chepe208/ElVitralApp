package com.example.elvitralapp.Screens

import androidx.compose.foundation.background
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
import com.example.elvitralapp.ui.theme.AccentBlue
import com.example.elvitralapp.ui.theme.DarkBackground
import com.example.elvitralapp.ui.theme.TextPrimary
import com.example.elvitralapp.ui.theme.TextSecondary

data class TechnicalVisit(
    val id: String,
    val clientName: String,
    val phone: String,
    val address: String,
    val status: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitManagerScreen(onBack: () -> Unit) {
    // Mock data for visits
    val visits = remember {
        mutableStateListOf(
            TechnicalVisit("1", "Juan Pérez", "555-0123", "Av. Reforma 123", "Pendiente", "2023-10-25"),
            TechnicalVisit("2", "María García", "555-4567", "Calle Juárez 45", "En Proceso", "2023-10-26"),
            TechnicalVisit("3", "Carlos López", "555-8901", "Blvd. Independencia 789", "Completada", "2023-10-24")
        )
    }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("Gestor de Visitas", color = TextPrimary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Administración de Visitas Técnicas",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(visits) { visit ->
                    VisitItem(
                        visit = visit,
                        onStatusChange = { newStatus ->
                            val index = visits.indexOfFirst { it.id == visit.id }
                            if (index != -1) {
                                visits[index] = visit.copy(status = newStatus)
                            }
                        }
                    )
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
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = visit.clientName,
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                
                StatusBadge(status = visit.status)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            InfoRow(icon = Icons.Default.Phone, text = visit.phone)
            InfoRow(icon = Icons.Default.LocationOn, text = visit.address)
            InfoRow(icon = Icons.Default.DateRange, text = visit.date)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box {
                    Button(
                        onClick = { showMenu = true },
                        colors = ButtonDefaults.buttonColors(containerColor = AccentBlue.copy(alpha = 0.2f)),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text("Cambiar Estado", color = AccentBlue, fontSize = 12.sp)
                    }
                    
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(Color(0xFF2C2C2C))
                    ) {
                        listOf("Pendiente", "En Proceso", "Completada", "Cancelada").forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status, color = Color.White) },
                                onClick = {
                                    onStatusChange(status)
                                    showMenu = false
                                }
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = TextSecondary, fontSize = 14.sp)
    }
}

@Composable
fun StatusBadge(status: String) {
    val backgroundColor = when (status) {
        "Pendiente" -> Color(0xFFFFB300).copy(alpha = 0.2f)
        "En Proceso" -> Color(0xFF2196F3).copy(alpha = 0.2f)
        "Completada" -> Color(0xFF4CAF50).copy(alpha = 0.2f)
        else -> Color(0xFFF44336).copy(alpha = 0.2f)
    }
    
    val textColor = when (status) {
        "Pendiente" -> Color(0xFFFFB300)
        "En Proceso" -> Color(0xFF2196F3)
        "Completada" -> Color(0xFF4CAF50)
        else -> Color(0xFFF44336)
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = status,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
