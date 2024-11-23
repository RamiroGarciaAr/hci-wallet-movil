package com.example.wallet_hci.app.screens.home

import com.example.wallet_hci.app.screens.home.ui.*
import com.example.wallet_hci.ui.layout.ViewModel
import com.example.wallet_hci.ui.theme.WallethciTheme
import com.example.wallet_hci.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.colorResource
import kotlinx.serialization.Serializable

// Related to the Card
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun HomeView(){
    Scaffold() {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(12.dp)
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
            Row() {
                CardWrapper(modifier = Modifier.weight(2f)){
                    Text("Card")
                }
                Spacer(modifier = Modifier.width(8.dp))
                CardWrapper(modifier = Modifier.weight(1f)){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.primary_100),
                                contentColor = colorResource(R.color.primary_500)
                            )
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {  
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add",
                                    tint = colorResource(R.color.primary_500),
                                    modifier = Modifier.size(32.dp)
                                )
                                Text("Ingresar")
                            }   
                        }
                    }
                }
            }
            CardWrapper{
                Text("Card")
            }
        }
    }
}