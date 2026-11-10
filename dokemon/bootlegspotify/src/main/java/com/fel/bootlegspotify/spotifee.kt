package com.fel.bootlegspotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fel.bootlegspotify.ui.theme.DokemonTheme
import com.fel.bootlegspotify.view.AlbumDetailScreen
import com.fel.bootlegspotify.view.ArtistScreen
import com.fel.bootlegspotify.viewmodel.AlbumDetailViewModel
import com.fel.bootlegspotify.viewmodel.ArtistViewModel

class spotifee : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DokemonTheme {
                SpotifeeApp()
            }
        }
    }
}

@Composable
fun SpotifeeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "artist") {
        composable("artist") {
            val viewModel: ArtistViewModel = viewModel()
            ArtistScreen(
                viewModel = viewModel,
                onAlbumClick = { albumId ->
                    navController.navigate("album_detail/$albumId")
                }
            )
        }
        composable(
            route = "album_detail/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            val viewModel: AlbumDetailViewModel = viewModel {
                AlbumDetailViewModel(albumId = albumId)
            }
            AlbumDetailScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
