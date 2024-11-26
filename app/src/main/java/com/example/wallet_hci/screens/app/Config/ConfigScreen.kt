package com.example.wallet_hci.screens.app.Config

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallet_hci.R

@Composable
fun ConfigurationAccordionMenu(viewModel: AccordionViewModel = viewModel()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sections
        item {
            AccordionItem(
                title = stringResource(id = R.string.personal),
                content = listOf(stringResource(id = R.string.name_label), stringResource(id = R.string.last_name_label), stringResource(id = R.string.email_placeholder)),
                viewModel = viewModel,
                txtBarMsg = listOf(stringResource(id = R.string.name_label), stringResource(id = R.string.last_name_label), stringResource(id = R.string.email_placeholder))
            )
        }
        item {
            AccordionItem(
                title = stringResource(id = R.string.contact_sec),
                content = listOf(stringResource(id = R.string.accordion_title_phone)),
                viewModel = viewModel,
                txtBarMsg = listOf(stringResource(id = R.string.accordion_title_phone))
            )
        }
        item {
            AccordionItem(
                title = stringResource(id = R.string.accordion_title_bank),
                content = listOf(stringResource(id = R.string.accordion_content_alias)),
                viewModel = viewModel,
                txtBarMsg = listOf(stringResource(id = R.string.accordion_content_alias)),
                isBank = true
            )
        }
        item {
            AccordionItem(
                title = stringResource(id = R.string.accordion_title_danger),
                content = listOf( stringResource(id = R.string.accordion_content_change_password), stringResource(id = R.string.accordion_content_delete_account)),
                isDangerZone = true,
                viewModel = viewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfigurationAccordionMenu() {
    MaterialTheme {
        ConfigurationAccordionMenu()
    }
}