package com.example.wallet_hci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition
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

class MainActivity : ComponentActivity() {


    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallethciTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavBar()
                    },
                    floatingActionButton = {
                        FloatingQRButton()
                    },
                ) { innerPadding ->
                    Greeting(
                        name = "Toto",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    @Composable
    fun MyApp() {
        this.navController = rememberNavController()

        // Seteamos la navegación
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Greeting("Mains")
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
