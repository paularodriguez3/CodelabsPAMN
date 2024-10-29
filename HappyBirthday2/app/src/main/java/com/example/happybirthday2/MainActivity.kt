package com.example.happybirthday2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.happybirthday2.ui.theme.HappyBirthday2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthday2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(name = "Paula", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("Happy birthday $name!") }
    var from by remember { mutableStateOf("Your sister, Irene") }

    Column(
        verticalArrangement= Arrangement.Center,
        horizontalAlignment= Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = message,
            fontSize = 36.sp,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = from,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                message = "Thanks for the wishes!"
                from = "From your sender"
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Change greeting")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HappyBirthday2Theme {
        Greeting(name = "Paula")
    }
}
