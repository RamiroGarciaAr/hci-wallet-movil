package com.example.wallet_hci.screens.app.Config

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallet_hci.R

@Composable
fun AccordionItem(
    title: String,
    content: List<String>,
    viewModel: AccordionViewModel,
    isDangerZone: Boolean = false,
    txtBarMsg: List<String>? = null
) {
    // Ensure the item is initialized in the ViewModel
    viewModel.ensureItemInitialized(title)

    // Observe the expanded state from the ViewModel
    val expanded = viewModel.expandedStates[title] == true
    val degrees = animateFloatAsState(if (expanded) 180f else 0f).value // Fixed here

    // Local state for the TextField input
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth() // Occupy the full width of the screen
                .clip(RoundedCornerShape(8.dp))
                .clickable { viewModel.toggleExpanded(title) }
                .background(
                    if (isDangerZone) MaterialTheme.colorScheme.error
                    else colorResource(R.color.blue_bar)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.rotate(degrees), // Apply rotation
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        // Expandable Content
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(
                spring(
                    stiffness = Spring.StiffnessMediumLow
                )
            ),
            exit = shrinkVertically(
                spring(
                    stiffness = Spring.StiffnessMediumLow
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                var idx = 0
                // Loop through content items
                content.forEach { item ->

                    if (isDangerZone) {
                        Button(
                            onClick = { onChangePass() },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(id = R.string.change_password))
                        }
                        Button(
                            onClick = { onDeleteAccount() },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(id = R.string.delete_account))
                        }
                    }
                    else
                    {

                        Text(
                            text = item,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = { Text(txtBarMsg?.getOrNull(idx) ?: "Type here") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    idx += 1

                }

            }
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    }
}

fun onDeleteAccount() {
    //TODO
}

fun onChangePass() {
    //TODO
}
