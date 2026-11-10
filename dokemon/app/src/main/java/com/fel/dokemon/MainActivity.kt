package com.fel.dokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fel.dokemon.ui.theme.DokemonTheme
import com.fel.dokemon.view.DokemonMainScreen
import com.fel.dokemon.viewmodel.DokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DokemonTheme {
                val viewModel: DokemonViewModel = viewModel()
                DokemonMainScreen(viewModel = viewModel)
            }
        }
    }
}
