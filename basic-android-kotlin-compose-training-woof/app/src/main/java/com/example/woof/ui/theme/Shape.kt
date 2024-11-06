package com.example.woof.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp), // Esquinas más redondeadas para botones pequeños
    medium = RoundedCornerShape(16.dp), // Tarjetas con esquinas redondeadas
    large = RoundedCornerShape(0.dp)   // Cuadrado para un estilo más "brutalista"
)
