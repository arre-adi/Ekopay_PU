package com.example.ekopay_pu

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.ekopay.bottomnav.MainScreen
import com.example.ekopay_pu.Screens.GreenCreditApp
import com.example.ekopay_pu.ui.theme.Ekopay_PUTheme

class MainActivity : ComponentActivity() {
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            setContent {

                    MainScreen()
            }
        } else {
            // Permission denied, close the app
            Toast.makeText(this, "Camera permission is required. Closing app.", Toast.LENGTH_LONG).show()
            finish() // Close the app if permission is denied
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check if the camera permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            // If permission is already granted, proceed
            setContent {
                    MainScreen()

            }
        } else {
            // Request permission
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

