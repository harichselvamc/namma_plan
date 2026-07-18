package com.hari.nammaplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier

import com.hari.nammaplan.ui.screens.SavingsScreen
import com.hari.nammaplan.ui.theme.NammaPlanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NammaPlanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SavingsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
