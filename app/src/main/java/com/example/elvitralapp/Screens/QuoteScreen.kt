package com.example.elvitralapp.Screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elvitralapp.data.api.RetrofitClient
import com.example.elvitralapp.data.model.Cotizacion
import com.example.elvitralapp.data.model.ProductoCotizado
import com.example.elvitralapp.data.repository.CotizacionRepository
import com.example.elvitralapp.ui.theme.LocalOnCycleTheme
import com.example.elvitralapp.ui.theme.LocalThemeMode
import com.example.elvitralapp.ui.theme.ThemeMode
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(onBack: () -> Unit) {
    val themeMode    = LocalThemeMode.current
    val onCycleTheme = LocalOnCycleTheme.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val cotizacionRepository = remember { CotizacionRepository(RetrofitClient.apiService) }

    val themeIcon = when (themeMode) {
        ThemeMode.DARK         -> Icons.Default.DarkMode
        ThemeMode.LIGHT        -> Icons.Default.LightMode
        ThemeMode.DARK_MEDIUM,
        ThemeMode.LIGHT_MEDIUM,
        ThemeMode.DARK_HIGH,
        ThemeMode.LIGHT_HIGH   -> Icons.Default.Contrast
    }

    var nombre     by remember { mutableStateOf("") }
    var email      by remember { mutableStateOf("") }
    var telefono   by remember { mutableStateOf("") }
    var direccion  by remember { mutableStateOf("") }

    var prodNombre by remember { mutableStateOf("") }
    var prodLargo  by remember { mutableStateOf("") }
    var prodAncho  by remember { mutableStateOf("") }
    var prodCant   by remember { mutableStateOf("1") }

    val productosSeleccionados = remember { mutableStateListOf<ProductoCotizado>() }
    
    val total = productosSeleccionados.sumOf { it.cantidad * it.precioUnitario }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Nueva Cotización", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Regresar")
                    }
                },
                actions = {
                    IconButton(onClick = onCycleTheme) {
                        Icon(themeIcon, "Cambiar tema", tint = MaterialTheme.colorScheme.primary)
                    }
                }
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
                    QuoteTextField(value = nombre,    onValueChange = { nombre = it },    label = "Nombre Completo")
                    QuoteTextField(value = email,     onValueChange = { email = it },     label = "Email")
                    QuoteTextField(value = telefono,  onValueChange = { telefono = it },  label = "Teléfono")
                    QuoteTextField(value = direccion, onValueChange = { direccion = it }, label = "Dirección")
                }
            }
            item {
                QuoteSection(title = "Agregar productos") {
                    QuoteTextField(value = prodNombre, onValueChange = { prodNombre = it }, label = "Tipo de Vidrio / Producto")
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        QuoteTextField(value = prodLargo, onValueChange = { prodLargo = it }, label = "Largo (cm)", modifier = Modifier.weight(1f))
                        QuoteTextField(value = prodAncho, onValueChange = { prodAncho = it }, label = "Ancho (cm)", modifier = Modifier.weight(1f))
                    }
                    QuoteTextField(value = prodCant, onValueChange = { prodCant = it }, label = "Cantidad")

                    Button(
                        onClick = {
                            if (prodNombre.isNotBlank() && prodLargo.isNotBlank() && prodAncho.isNotBlank()) {
                                val largoVal = prodLargo.toDoubleOrNull() ?: 0.0
                                val anchoVal = prodAncho.toDoubleOrNull() ?: 0.0
                                val cantVal  = prodCant.toIntOrNull() ?: 1
                                val precio = ((largoVal * anchoVal) * 3.5).roundToInt().toDouble()
                                
                                productosSeleccionados.add(
                                    ProductoCotizado(prodNombre, largoVal, anchoVal, cantVal, precio)
                                )
                                prodNombre = ""; prodLargo = ""; prodAncho = ""; prodCant = "1"
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Agregar al listado")
                    }

                    if (productosSeleccionados.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Productos Seleccionados", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        
                        productosSeleccionados.forEach { prod ->
                            SelectedProductItem(
                                name = prod.nombre,
                                details = "${prod.largo}x${prod.ancho}cm x ${prod.cantidad} und",
                                price = "$${(prod.precioUnitario * prod.cantidad).toInt()}",
                                onDelete = { productosSeleccionados.remove(prod) }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Total Estimado:", fontWeight = FontWeight.Bold)
                                Text("${total.toInt()}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                        }

                        Button(
                            onClick = {
                                if (nombre.isNotBlank() && email.isNotBlank()) {
                                    scope.launch {
                                        try {
                                            // Paso CRITICO: Convertir la lista a un String plano para que el servidor NO falle
                                            val gson = Gson()
                                            val listAsJsonString = gson.toJson(productosSeleccionados.toList())

                                            val cotizacion = Cotizacion(
                                                nombreCliente = nombre,
                                                email = email,
                                                telefono = telefono,
                                                direccion = direccion,
                                                productosJson = listAsJsonString, 
                                                total = total.toInt()
                                            )
                                            
                                            Log.d("API_FINAL", "Enviando cotizacion: $cotizacion")
                                            
                                            cotizacionRepository.createCotizacion(cotizacion)
                                            snackbarHostState.showSnackbar("Cotización enviada con éxito")
                                            onBack()
                                        } catch (e: Exception) {
                                            Log.e("API_FINAL", "ERROR: ${e.message}", e)
                                            val errorBody = (e as? retrofit2.HttpException)?.response()?.errorBody()?.string()
                                            snackbarHostState.showSnackbar("Error ${e.hashCode()}: $errorBody")
                                        }
                                    }
                                } else {
                                    scope.launch { snackbarHostState.showSnackbar("Completa nombre y correo") }
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(54.dp).padding(top = 8.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Generar y Enviar Cotización", fontWeight = FontWeight.ExtraBold)
                        }
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(title, color = MaterialTheme.colorScheme.onSurface, fontSize = 19.sp, fontWeight = FontWeight.Bold)
            content()
        }
    }
}

@Composable
fun QuoteTextField(value: String, onValueChange: (String) -> Unit, label: String, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label, fontSize = 14.sp) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun SelectedProductItem(name: String, details: String, price: String, onDelete: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(details, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                Text(price, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Eliminar", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(20.dp))
            }
        }
    }
}
