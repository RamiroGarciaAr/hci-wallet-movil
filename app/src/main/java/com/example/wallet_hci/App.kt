package com.example.wallet_hci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallet_hci.ui.theme.WallethciTheme
import com.example.wallet_hci.ui.menu.FloatingQRButton
import com.example.wallet_hci.ui.menu.NavBar
import com.example.wallet_hci.app.routes.Navigator
import com.example.wallet_hci.data.network.RemoteDataSource
import com.example.wallet_hci.data.UserRemoteDataSource
import com.example.wallet_hci.data.repository.UserRepository

class MainActivity : ComponentActivity() {
    private lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sessionManager = SessionManager(this)
        navigator = Navigator()
        setContent {
            WallethciTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavBar(this.navigator)
                    },
                    floatingActionButton = {
                        FloatingQRButton()
                    },
                ) {_ ->  this.navigator.Routes() }
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
