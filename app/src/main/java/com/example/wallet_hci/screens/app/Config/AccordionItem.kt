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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
    isBank: Boolean = false,
    txtBarMsg: List<String>? = null
) {
    // Ensure the item is initialized in the ViewModel
    viewModel.ensureItemInitialized(title)

    // Observe the expanded state from the ViewModel
    val expanded = viewModel.expandedStates[title] == true
    val degrees = animateFloatAsState(if (expanded) 180f else 0f).value

    // Local state for each TextField input
    val textFieldValues = remember { mutableStateListOf(*Array(content.size) { "" }) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { viewModel.toggleExpanded(title) }
                .background(
                    if (isDangerZone) MaterialTheme.colorScheme.error
                    else colorResource(R.color.primary_500)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.rotate(degrees),
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
                // Display buttons for Danger Zone
                if (isDangerZone) {
                    Button(
                        onClick = { onChangePass() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.change_password))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onDeleteAccount() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.delete_account))
                    }
                }
                else {
                    // Display content items and TextFields
                    content.forEachIndexed { index, item ->
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        OutlinedTextField(
                            value = textFieldValues[index],
                            label = { Text(txtBarMsg?.getOrNull(index) ?: "Type here") },
                            onValueChange = { textFieldValues[index] = it },
                            placeholder = { Text(txtBarMsg?.getOrNull(index) ?: "Type Here") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedBorderColor = Color.Blue
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Display button for Bank Section
                    if (isBank) {
                        Button(
                            onClick = { /* Handle bank action */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.teal_700)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(text = stringResource(id = R.string.new_card))

                        }
                    }
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
    // TODO: Implement delete account functionality
}

fun onChangePass() {
    // TODO: Implement change password functionality
}
