package com.na.rmuniverse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.na.rmuniverse.presentation.screen.CharactersListScreen
import com.na.rmuniverse.presentation.theme.RMUniverseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RMUniverseTheme {
                Scaffold { innerPadding ->
                    CharactersListScreen(paddingValues = innerPadding)
                }
            }
        }
    }
}