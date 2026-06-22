package com.example.elvitralapp.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elvitralapp.data.api.RetrofitClient
import com.example.elvitralapp.data.model.Visita
import com.example.elvitralapp.data.repository.VisitaRepository
import com.example.elvitralapp.data.viewmodel.ViewModelFactory
import com.example.elvitralapp.data.viewmodel.VisitaViewModel
import com.example.elvitralapp.ui.theme.LocalOnCycleTheme
import com.example.elvitralapp.ui.theme.LocalThemeMode
import com.example.elvitralapp.ui.theme.ThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicalVisitScreen(
    onBack: () -> Unit,
    viewModel: VisitaViewModel = viewModel(
        factory = ViewModelFactory(VisitaRepository(RetrofitClient.apiService))
    )
) {
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

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Solicitud de Visita",
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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Build, null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(80.dp))

            Spacer(modifier = Modifier.height(24.dp))

            Text("Visita Técnica",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Text(
                "Déjanos tus datos y un experto te visitará para asesorarte en tu proyecto.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp))

            VisitTextField("Nombre del solicitante", Icons.Default.Person, name) { name = it }
            Spacer(modifier = Modifier.height(16.dp))
            VisitTextField("Teléfono de contacto",   Icons.Default.Phone, phone) { phone = it }
            Spacer(modifier = Modifier.height(16.dp))
            VisitTextField("Dirección de la obra",   Icons.Default.LocationOn, address) { address = it }
            Spacer(modifier = Modifier.height(16.dp))
            VisitTextField("Descripción del proyecto", Icons.Default.Description, description) { description = it }

            Spacer(modifier = Modifier.height(32.dp))

            if (error != null) {
                Text(text = error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(bottom = 8.dp))
            }

            Button(
                onClick = {
                    val newVisita = Visita(
                        name = name,
                        phone = phone,
                        address = address,
                        service = "Visita Técnica",
                        date = "", // Default or to be filled later
                        time = "",
                        description = description
                    )
                    viewModel.createVisita(newVisita) {
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = !isLoading && name.isNotBlank() && phone.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                } else {
                    Text("Enviar Solicitud", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun VisitTextField(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        singleLine = true
    )
}
