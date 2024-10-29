package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.artspace.ui.theme.ArtSpaceTheme

data class Artwork(val imageRes: Int, val title: String, val artist: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentIndex by remember { mutableStateOf(0) }
    val artworks = listOf(
        Artwork(R.drawable.art1, "Starry Night", "Vincent van Gogh"),
        Artwork(R.drawable.art2, "The Scream", "Edvard Munch"),
        Artwork(R.drawable.art3, "Mona Lisa", "Leonardo da Vinci")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtDisplay(artwork = artworks[currentIndex])
        Spacer(modifier = Modifier.height(16.dp))
        NavigationButtons(
            onPreviousClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
            },
            onNextClick = {
                currentIndex = (currentIndex + 1) % artworks.size
            }
        )
    }
}

@Composable
fun ArtDisplay(artwork: Artwork) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = artwork.imageRes),
            contentDescription = artwork.title,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = artwork.title,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "by ${artwork.artist}",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun NavigationButtons(onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onPreviousClick, modifier = Modifier.weight(1f)) {
            Text("Anterior")
        }

        Button(onClick = onNextClick, modifier = Modifier.weight(1f)) {
            Text("Siguiente")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
