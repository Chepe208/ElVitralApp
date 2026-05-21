package com.example.elvitralapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elvitralapp.ui.components.Footer
import com.example.elvitralapp.ui.components.Navbar
import com.example.elvitralapp.ui.theme.AccentBlue
import com.example.elvitralapp.ui.theme.DarkBackground
import com.example.elvitralapp.ui.theme.DarkSurface
import com.example.elvitralapp.ui.theme.ElVitralAppTheme
import com.example.elvitralapp.ui.theme.TextPrimary
import com.example.elvitralapp.ui.theme.TextSecondary

@Composable
fun LandingPage() {
    Scaffold(
        topBar = { Navbar() },
        containerColor = DarkBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { HeroSection() }
            item { FeaturedProjectsSection() }
            item { TestimonialsSection() }
            item { ContactSection() }
            item { Footer() }
        }
    }
}

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        // Simulación de la imagen de fondo con degradado (balcón/ciudad)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A1C22),
                            DarkBackground
                        )
                    )
                )
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Buscando nuevas instalaciones de vidrio?",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Barra de búsqueda optimizada para móvil
            Surface(
                color = DarkSurface.copy(alpha = 0.9f),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FilterItemSmall("Tipo")
                        FilterItemSmall("Aplicación")
                        FilterItemSmall("Servicio")
                    }
                    
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Buscar Proyectos", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun FilterItemSmall(label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, color = TextSecondary, fontSize = 12.sp)
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun FeaturedProjectsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeader("Proyectos destacados", "Descubre nuestros últimos trabajos en cristalería")

        // En móvil los apilamos para que se vean centrados y ordenados
        ProjectCard(
            "Fachada Comercial",
            "Instalación de vidrio templado para centro comercial.",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ProjectCard(
            "Divisiones Corporativas",
            "Separadores de ambiente en vidrio laminado acústico.",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ProjectCard(
            "Barandas Residenciales",
            "Diseño e instalación de barandas de cristal para exteriores."
        )
    }
}

@Composable
fun ProjectCard(title: String, description: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, null, tint = Color.DarkGray, modifier = Modifier.size(48.dp))
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, color = TextSecondary, fontSize = 13.sp, lineHeight = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Ver detalles", color = AccentBlue, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = AccentBlue,
                        modifier = Modifier.size(14.dp).padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TestimonialsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeader("Lo que dicen nuestros clientes", "Reseñas reales de Google Maps")

        TestimonialCard("Maria González", "Excelente servicio, muy puntuales.", modifier = Modifier.padding(bottom = 12.dp))
        TestimonialCard("Carlos Rodriguez", "La calidad del vidrio es insuperable.", modifier = Modifier.padding(bottom = 12.dp))
        TestimonialCard("Ana López", "Muy profesionales en la instalación.")
    }
}

@Composable
fun TestimonialCard(name: String, comment: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(AccentBlue.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                    Text(name.take(1), color = AccentBlue, fontWeight = FontWeight.Bold)
                }
                Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                    Text(text = name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = "Google Review", color = TextSecondary, fontSize = 11.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("5.0", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.Star, null, tint = Color(0xFFFFD700), modifier = Modifier.size(14.dp).padding(start = 2.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "\"$comment\"", color = TextSecondary, fontSize = 13.sp, lineHeight = 20.sp)
        }
    }
}

@Composable
fun ContactSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeader("Visítanos", "Estamos ubicados en el corazón de Medellín")

        Column(modifier = Modifier.fillMaxWidth()) {
            ContactItem(Icons.Default.LocationOn, "Dirección", "Calle 30 # 73-26, Medellín")
            ContactItem(Icons.Default.Phone, "Teléfono", "+57 313 792 84 53")
            ContactItem(Icons.Default.Email, "Email", "ventas@elvitral.com")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Simulación de Mapa
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray.copy(alpha = 0.1f))
                .border(0.5.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Map, null, tint = TextSecondary, modifier = Modifier.size(40.dp))
                Text("Cargando mapa...", color = TextSecondary, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String) {
    Text(
        text = title,
        color = TextPrimary,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Text(
        text = subtitle,
        color = TextSecondary,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
    )
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, detail: String) {
    Row(modifier = Modifier.padding(bottom = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = AccentBlue, modifier = Modifier.size(24.dp))
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = title, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = detail, color = TextSecondary, fontSize = 13.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPreview() {
    ElVitralAppTheme {
        LandingPage()
    }
}
