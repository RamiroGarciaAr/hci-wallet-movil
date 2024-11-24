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
import com.example.wallet_hci.app.routes.Navigator
import com.example.wallet_hci.ui.theme.WallethciTheme
import com.example.wallet_hci.ui.menu.NavBar
import com.example.wallet_hci.ui.menu.FloatingQRButton
import com.example.wallet_hci.ui.menu.TopBar
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
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
                    bottomBar = { NavBar(this.navigator) },
                    floatingActionButton = { FloatingQRButton() },

                ) { innerPadding ->  
                    Column(modifier = Modifier.padding(innerPadding))
                    { navigator.Routes() }
                }
            }
        }
    }
}
