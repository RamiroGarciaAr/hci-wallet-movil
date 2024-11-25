package com.example.wallet_hci.app.screens.home

import com.example.wallet_hci.app.screens.home.ui.*
import com.example.wallet_hci.app.routes.Navigator
import com.example.wallet_hci.app.routes.NavigatorProvider
import com.example.wallet_hci.app.routes.Routes
import com.example.wallet_hci.ui.layout.ViewModel
import com.example.wallet_hci.ui.theme.WallethciTheme
import com.example.wallet_hci.ui.plots.ExpensesPlot
import com.example.wallet_hci.ui.plots.GainPlot
import com.example.wallet_hci.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable

// Related to the Card
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.window.core.layout.WindowWidthSizeClass


data class Action (val icon: Int, val text: Int, val onClick: () -> Unit);

@Composable
fun HomeView(){

    val navigator = NavigatorProvider.current

    val actions = listOf(
        Action(R.drawable.fa_money_bills, R.string.deposit, onClick = {
            navigator.navigateTo(Routes.Activity)
        }),
        Action(R.drawable.fa_paper_plane, R.string.spend, onClick = {
            navigator.navigateTo(Routes.Contacts)
        }),
        Action(R.drawable.fa_money_bill_transfer, R.string.transfer, onClick = {
            val routeTransfer = Routes.Transfer()
            navigator.navigateTo(routeTransfer)
        }),
        Action(R.drawable.fa_address_card, R.string.cvu, onClick = {
            navigator.navigateTo(Routes.Profile)
        })
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 12.dp)
        .verticalScroll(rememberScrollState())
        
        ) {
            CardWrapper {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) { 
                    AvailableMoney()
                    CardHolder()
                }
            }
            Row(
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max)
            ) {

                ExpensesPlot( modifier = Modifier.weight(2f))
                Spacer(modifier = Modifier.width(8.dp))
                CardWrapper(modifier = Modifier.weight(1.15f)){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        actions.forEach { action ->
                            ActionButton(
                                painter = painterResource(action.icon),
                                text = stringResource(action.text),
                                onClick = action.onClick
                            )
                        }
                    }
                }
            }
            GainPlot( modifier = Modifier
                .height(200.dp) 
            )
        }
}