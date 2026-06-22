package com.example.elvitralapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elvitralapp.ui.theme.DarkBackground
import com.example.elvitralapp.ui.theme.TextPrimary
import com.example.elvitralapp.ui.theme.TextSecondary

@Composable
fun Footer(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "EL VITRAL",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Especialistas en soluciones de vidrio, espejos y herrajes para tu hogar y negocio.",
                color = TextSecondary,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    FooterTitle("Productos")
                    FooterItem("Vidrio")
                    FooterItem("Espejos")
                    FooterItem("Aluminio")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    FooterTitle("Servicios")
                    FooterItem("Catálogo")
                    FooterItem("Cotizar")
                    FooterItem("Proyectos")
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
        HorizontalDivider(color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "© 2024 El Vitral. Todos los derechos reservados.",
            color = TextSecondary,
            fontSize = 11.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Medellín, Colombia",
            color = TextSecondary.copy(alpha = 0.7f),
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Área de gestión",
            color = TextSecondary.copy(alpha = 0.35f),
            fontSize = 10.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable { navController?.navigate("visit_manager") }
                .padding(4.dp)
        )
    }
}

@Composable
private fun FooterTitle(text: String) {
    Text(
        text = text.uppercase(),
        color = TextPrimary,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
private fun FooterItem(text: String) {
    Text(
        text = text,
        color = TextSecondary,
        fontSize = 12.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}