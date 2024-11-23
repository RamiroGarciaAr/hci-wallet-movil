package com.example.wallet_hci

import android.os.Bundle
import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallet_hci.ui.theme.WallethciTheme

import androidx.navigation.compose.rememberNavController
import com.example.wallet_hci.ui.menu.FloatingQRButton
import com.example.wallet_hci.ui.menu.NavBar
import com.example.wallet_hci.app.routes.Navigator

class MainActivity : ComponentActivity() {
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        navigator = Navigator()

        setContent {
            WallethciTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavBar(navigator)
                    },
                    floatingActionButton = {
                        FloatingQRButton()
                    },
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        navigator.Routes() // Renderiza las pantallas directamente
                    }
                }
            }
        }
    }
}


// Seteamos las rutas de las vistas

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WallethciTheme {
        Greeting("Main")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Tobias $name!",
        modifier = modifier
    )
}
