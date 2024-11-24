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
import com.example.wallet_hci.R
import com.example.wallet_hci.app.routes.Navigator

import com.example.wallet_hci.app.routes.NavigatorProvider

@Composable
fun NavBar() {

    val navigator = NavigatorProvider.current
    var selectedItem by remember { mutableIntStateOf(0) }

    val routes = listOf(
        "settings",
        "home",
        "activity",
        "contacts"
    )
    var currentPage by remember { mutableStateOf("home") }

    val items = listOf(
        stringResource(R.string.r_settings), 
        stringResource(R.string.r_home), 
        stringResource(R.string.r_activities), 
        // stringResource(R.string.r_contacts)
    )
    val selectedIcons = listOf(
        Icons.Filled.Settings, 
        Icons.Filled.Home, 
        Icons.Filled.Favorite, 
        Icons.Filled.Star
    )
    val unselectedIcons = listOf(
        Icons.Outlined.Settings, 
        Icons.Outlined.Home, 
        Icons.Outlined.FavoriteBorder, 
        Icons.Outlined.Star
    )
    NavigationBar(
        containerColor = colorResource(R.color.primary_100),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (currentPage.compareTo(routes[index]) == 0) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item,
                        tint = colorResource(R.color.primary_500),
                    )
                },
                label = { Text(item) },
                selected = currentPage.compareTo(routes[index]) == 0,
                onClick = { 
                    currentPage = routes[index]
                    navigator.navigateTo(currentPage)
                },
            )
        }
    }
}