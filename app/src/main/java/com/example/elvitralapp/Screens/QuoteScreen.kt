package com.example.elvitralapp.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elvitralapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Cotización", color = TextPrimary, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        QuoteTextField(label = "Tipo de Vidrio", modifier = Modifier.weight(1.2f))
                        QuoteTextField(label = "Largo (cm)", modifier = Modifier.weight(0.8f))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        QuoteTextField(label = "Ancho (cm)", modifier = Modifier.weight(1f))
                        QuoteTextField(label = "Cantidad", modifier = Modifier.weight(1f))
                    }
                    
                    TextButton(
                        onClick = { },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Agregar Producto",
                            color = AccentBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Productos Seleccionados",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    
                    SelectedProductItem(
                        name = "Vidrio Claro 3mm",
                        details = "10x10cm x 1 und",
                        price = "$350"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = DarkBackground,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total:", color = TextPrimary, fontWeight = FontWeight.Bold)
                            Text("$350", color = TextPrimary, fontWeight = FontWeight.Bold)
                        }
                    }

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Generar cotización", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
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
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteTextField(label: String, modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(label, color = TextSecondary.copy(alpha = 0.6f), fontSize = 14.sp) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = DarkBackground,
            unfocusedContainerColor = DarkBackground,
            disabledContainerColor = DarkBackground,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = AccentBlue,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun SelectedProductItem(name: String, details: String, price: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = DarkBackground,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = details, color = TextSecondary, fontSize = 12.sp)
            }
            Text(text = price, color = AccentBlue, fontWeight = FontWeight.Bold)
        }
    }
}
