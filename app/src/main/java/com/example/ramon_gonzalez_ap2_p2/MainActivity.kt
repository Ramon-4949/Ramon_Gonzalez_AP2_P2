package com.example.ramon_gonzalez_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.example.ramon_gonzalez_ap2_p2.presentation.navigation.JugadorNavHost
import com.example.ramon_gonzalez_ap2_p2.ui.theme.Ramon_Gonzalez_AP2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ramon_Gonzalez_AP2_P2Theme {
                JugadorNavHost()
            }
        }
    }
}