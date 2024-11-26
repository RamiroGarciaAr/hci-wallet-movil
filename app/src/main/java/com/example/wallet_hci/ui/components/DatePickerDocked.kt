// package com.example.wallet_hci.ui.components

// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.remember
// import androidx.compose.material3.rememberDatePickerState
// import androidx.compose.material3.OutlinedTextField
// import androidx.compose.material3.IconButton
// import androidx.compose.ui.window.Popup
// import androidx.compose.foundation.layout.*

// import androidx.compose.material.icons.Icons
// import androidx.compose.material.icons.filled.DateRange
// import androidx.compose.material3.Icon
// import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.Text
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.unit.dp

// import java.lang.reflect.Modifier

// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun DatePickerDocked() {
//     var showDatePicker by remember { mutableStateOf(false) }
//     val datePickerState = rememberDatePickerState()
//     val selectedDate = datePickerState.selectedDateMillis?.let {
//         convertMillisToDate(it)
//     } ?: ""

//     Box(
//         modifier = Modifier.fillMaxWidth()
//     ) {
//         OutlinedTextField(
//             value = selectedDate,
//             onValueChange = { },
//             label = { Text("DOB") },
//             readOnly = true,
//             trailingIcon = {
//                 IconButton(onClick = { showDatePicker = !showDatePicker }) {
//                     Icon(
//                         imageVector = Icons.Default.DateRange,
//                         contentDescription = "Select date"
//                     )
//                 }
//             },
//             modifier = Modifier
//                 .fillMaxWidth()
//                 .height(64.dp)
//         )

//         if (showDatePicker) {
//             Popup(
//                 onDismissRequest = { showDatePicker = false },
//                 alignment = Alignment.TopStart
//             ) {
//                 Box(
//                     modifier = Modifier
//                         .fillMaxWidth()
//                         .offset(y = 64.dp)
//                         .shadow(elevation = 4.dp)
//                         .background(MaterialTheme.colorScheme.surface)
//                         .padding(16.dp)
//                 ) {
//                     DatePicker(
//                         state = datePickerState,
//                         showModeToggle = false
//                     )
//                 }
//             }
//         }
//     }
// }