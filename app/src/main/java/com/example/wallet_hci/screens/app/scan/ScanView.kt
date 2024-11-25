package com.example.wallet_hci.screens.app.scan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.wallet_hci.MainActivity
import android.Manifest
import android.util.Log
import com.example.wallet_hci.R
import com.example.wallet_hci.app.routes.NavigatorProvider
import com.example.wallet_hci.screens.app.scan.ScannerPreview
import com.journeyapps.barcodescanner.CompoundBarcodeView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.remember

import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import com.google.accompanist.permissions.rememberPermissionState

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import androidx.activity.result.contract.ActivityResultContracts

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanView() {
    val context = LocalContext.current
    val navigator = NavigatorProvider.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) { 
            ScannerPreview()
         } else {
            // ActivityResultContracts.RequestPermission()
            navigator.navigateBack()
        }
    }
    
    LaunchedEffect(cameraPermissionState){
        if (cameraPermissionState.status.isGranted || !cameraPermissionState.status.shouldShowRationale) {
            launcher.launch(android.Manifest.permission.CAMERA)
        }
    }

    // val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    // PermissionRequired(
    //     permissionState = permissionState,
    //     permissionNotGrantedContent = {
    //         Column(
    //             verticalArrangement = Arrangement.Center,
    //             horizontalAlignment = Alignment.CenterHorizontally,
    //             modifier = Modifier.fillMaxSize()
    //         ) 
    //         {
    //             Text(text = stringResource(R.string.need_camera_permission_to_scan) , style = MaterialTheme.typography.bodyLarge)
    //         }
    //     },
    //     permissionNotAvailableContent = { }
    // ) { ScannerPreview() }
}