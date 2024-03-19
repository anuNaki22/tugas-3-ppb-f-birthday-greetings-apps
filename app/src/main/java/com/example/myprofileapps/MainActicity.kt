package com.example.birthdaygreetingsapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.background
//import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
//import androidx.compose.animation.*
//import androidx.compose.animation.core.*
//import kotlin.math.ceil
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.birthdaygreetingsapps.R

//import androidx.compose.ui.res.painterResource

private val RedThemeColors = lightColors(
    primary = Color(0xFFF44336), // Merah terang
    primaryVariant = Color(0xFFD32F2F), // Merah sedikit lebih gelap
    secondary = Color(0xFFFFC107) // Amber sebagai warna sekunder
)

@Composable
fun RedTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = RedThemeColors,
//        typography = Typography,
//        shapes = Shapes,
        content = content
    )
}

// Fungsi untuk halaman ucapan "Happy Birthday"
@Composable
fun BirthdayGreetingPage() {
    var isGiftOpened by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Conditional rendering for the "Happy Birthday Kamu!" text
        if (isGiftOpened) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFEC407A), // Light Pink
                                Color(0xFFB71C1C) // Dark Red
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Happy Birthday Kamu!",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Image of the gift
        Image(
            painter = if (isGiftOpened) painterResource(id = R.drawable.gift_opened) else painterResource(id = R.drawable.gift_closed),
            contentDescription = if (isGiftOpened) "Opened Gift" else "Closed Gift",
            modifier = Modifier.size(width = if (isGiftOpened) 300.dp else 200.dp, height = if (isGiftOpened) 300.dp else 200.dp)
        )

        // Text "from Rahmat" below the gift
        if (isGiftOpened) {
            Text(
                text = "from: Rahmat Faris Akbar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to open/close the gift
        Button(
            onClick = { isGiftOpened = !isGiftOpened },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEC407A))
        ) {
            Text(
                if (isGiftOpened) "Close Gift" else "Open Gift",
                color = Color.White
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RedTheme {
                BirthdayGreetingPage()
            }
        }
    }
}
