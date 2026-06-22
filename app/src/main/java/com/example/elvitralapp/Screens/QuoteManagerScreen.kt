package com.example.elvitralapp.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elvitralapp.data.api.RetrofitClient
import com.example.elvitralapp.data.model.Cotizacion
import com.example.elvitralapp.ui.theme.LocalOnCycleTheme
import com.example.elvitralapp.ui.theme.LocalThemeMode
import com.example.elvitralapp.ui.theme.ThemeMode
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteManagerScreen(onBack: () -> Unit) {
    val themeMode = LocalThemeMode.current
    val onCycleTheme = LocalOnCycleTheme.current
    val scope = rememberCoroutineScope()

    var cotizaciones by remember { mutableStateOf<List<Cotizacion>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    fun loadCotizaciones() {
        scope.launch {
            isLoading = true
            error = null
            try {
                cotizaciones = RetrofitClient.apiService.getCotizaciones()
            } catch (e: Exception) {
                error = "Error al cargar cotizaciones: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadCotizaciones()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestor de Cotizaciones", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Regresar")
                    }
                },
                actions = {
                    IconButton(onClick = { loadCotizaciones() }) {
                        Icon(Icons.Default.Refresh, "Refrescar")
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }
        } else if (cotizaciones.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay cotizaciones registradas", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cotizaciones) { cotizacion ->
                    QuoteCard(cotizacion)
                }
            }
        }
    }
}

@Composable
fun QuoteCard(cotizacion: Cotizacion) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cotizacion.nombreCliente,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "$${String.format("%.2f", cotizacion.total)}",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            Text(
                text = cotizacion.email,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Productos:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            cotizacion.productos.forEach { prod ->
                Text(
                    text = "• ${prod.nombre} (${prod.largo}x${prod.ancho}cm) x${prod.cantidad}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = cotizacion.direccion,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
