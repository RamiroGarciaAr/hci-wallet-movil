package com.example.wallet_hci.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.wallet_hci.R


@Composable
fun VerificationCodeInput(
    code: List<String>,
    onCodeChanged: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    textFieldSize: Dp = 60.dp,
    focusRequesters: List<FocusRequester> = List(code.size) { FocusRequester() }
) {
    Text(
        text = stringResource(id = R.string.verification_code),
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.primary_600),
        fontSize = 18.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        code.forEachIndexed { index, codeValue ->
            OutlinedTextField(
                value = codeValue,
                placeholder = { Text("0") },
                onValueChange = { newValue ->
                    if (newValue.length <= 1) { // Allow only one character
                        onCodeChanged(index, newValue)

                        if (newValue.isNotEmpty() && index < code.size - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .size(textFieldSize)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent { event ->
                        if (event.key == Key.Backspace && index > 0) {
                            focusRequesters[index - 1].requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedBorderColor = Color.Blue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }
    }
}

