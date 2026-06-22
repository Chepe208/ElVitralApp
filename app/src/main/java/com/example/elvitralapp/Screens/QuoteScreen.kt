package com.example.elvitralapp.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elvitralapp.ui.theme.LocalOnCycleTheme
import com.example.elvitralapp.ui.theme.LocalThemeMode
import com.example.elvitralapp.ui.theme.ThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(onBack: () -> Unit) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Cotización",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Regresar",
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
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(bottom = 32.dp, top = 16.dp)
        ) {
            item {
                QuoteSection(title = "Tus datos") {
                    QuoteTextField(label = "Nombre Completo")
                    QuoteTextField(label = "Email")
                    QuoteTextField(label = "Teléfono")
                    QuoteTextField(label = "Dirección")
                }
            }
            item {
                QuoteSection(title = "Agregar productos") {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        QuoteTextField("Tipo de Vidrio", modifier = Modifier.weight(1.2f))
                        QuoteTextField("Largo (cm)",     modifier = Modifier.weight(0.8f))
                    }
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        QuoteTextField("Ancho (cm)", modifier = Modifier.weight(1f))
                        QuoteTextField("Cantidad",   modifier = Modifier.weight(1f))
                    }

                    TextButton(onClick = { }, contentPadding = PaddingValues(0.dp)) {
                        Text("Agregar Producto",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Productos Seleccionados",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp)

                    SelectedProductItem("Vidrio Claro 3mm", "10x10cm x 1 und", "$350")

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total:", color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold)
                            Text("$350",  color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold)
                        }
                    }

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(54.dp).padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Generar cotización",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold)
            content()
        }
    }
}

@Composable
fun QuoteTextField(label: String, modifier: Modifier = Modifier) {
    TextField(
        value = "", onValueChange = {},
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(label,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            fontSize = 14.sp) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun SelectedProductItem(name: String, details: String, price: String) {
    Surface(modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(12.dp)) {
        Row(modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(name, color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(details, color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp)
            }
            Text(price, color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)
        }
    }
}