// Built-in Navigation for Compose
package com.example.wallet_hci.ui.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import com.example.wallet_hci.R
import com.example.wallet_hci.app.routes.Navigator

import com.example.wallet_hci.app.routes.Routes
import com.example.wallet_hci.routes.NavigatorProvider

@Composable
fun NavBar() {

    val navigator = NavigatorProvider.current
    val items = listOf(
        "settings",
        "home",
        "activity",
        "contacts"
    )

    val routes = listOf(
        Routes.Settings,
        Routes.Home,
        Routes.Activity,
        Routes.Contacts
    )
    var currentPage by remember { mutableStateOf("home") }
    NavigationBar(
        containerColor = colorResource(R.color.primary_100),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        routes[index].getIcon(currentPage.compareTo(item) == 0),
                        contentDescription = routes[index].getName(),
                        tint = colorResource(R.color.primary_500),
                    )
                },
                label = { Text(routes[index].getName()) },
                selected = currentPage.compareTo(item) == 0,
                onClick = { 
                    currentPage = item
                    navigator.navigateTo(routes[index])
                },
            )
        }
    }
}