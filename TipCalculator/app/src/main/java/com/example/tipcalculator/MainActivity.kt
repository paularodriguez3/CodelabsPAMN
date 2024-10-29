package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                TipTimeScreen()
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15f) }
    var tipResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de propinas",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text(
            text = "Porcentaje de propina: ${tipPercentage.toInt()}%",
            modifier = Modifier.padding(top = 16.dp)
        )
        Slider(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            valueRange = 0f..30f,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                val amount = amountInput.toDoubleOrNull() ?: 0.0
                tipResult = calculateTip(amount, tipPercentage)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Calcular propina")
        }
        Text(
            text = "Propina: $tipResult",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

fun calculateTip(amount: Double, tipPercent: Float): String {
    val tip = amount * (tipPercent / 100)
    return String.format("%.2f", tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeScreenPreview() {
    TipCalculatorTheme {
        TipTimeScreen()
    }
}
