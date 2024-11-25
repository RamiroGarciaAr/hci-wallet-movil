package com.example.wallet_hci.screens.app.scan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.random.Random
import com.journeyapps.barcodescanner.CompoundBarcodeView
import android.app.Activity
import com.journeyapps.barcodescanner.CaptureManager
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun ScannerPreview() {
    var scanFlag by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }
    var lastReadBarcode by remember { mutableStateOf<String?>(null) }
    var torchState by remember { mutableStateOf(false) }
    var recomposeFlag by remember { mutableIntStateOf(Random.nextInt()) }
    
    key(recomposeFlag) {
        AndroidView(
            factory = { context ->
                val preview = CompoundBarcodeView(context)
                preview.setStatusText("")
                preview.cameraSettings.isAutoTorchEnabled = torchState
                preview.apply {
                    val capture = CaptureManager(context as Activity, this)
                    capture.initializeFromIntent(context.intent, null)
                    capture.decode()
                    this.decodeContinuous { result ->
                        if (scanFlag) {
                            return@decodeContinuous
                        }
                        scanFlag = true
                        result.text?.let { barCodeOrQr ->
                            lastReadBarcode = result.text
                            scanFlag = true                    
                            showResult = true
                        }
                    }
                    this.resume()
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}