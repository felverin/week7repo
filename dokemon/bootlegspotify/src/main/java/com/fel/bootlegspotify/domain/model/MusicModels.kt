package com.fel.bootlegspotify.domain.model

data class Artist(
    val id: String,
    val name: String,
    val genre: String,
    val biography: String,
    val thumbUrl: String?,
    val bannerUrl: String?
)

data class Album(
    val id: String,
    val title: String,
    val artistName: String,
    val releaseYear: String,
    val genre: String,
    val description: String,
    val thumbUrl: String?
)

data class Track(
    val id: String,
    val trackNumber: Int,
    val title: String,
    val durationMs: Long?
)

sealed interface ArtistUiState {
    data object Loading : ArtistUiState
    data class Success(
        val artist: Artist,
        val albums: List<Album>
    ) : ArtistUiState
    data class Error(val message: String) : ArtistUiState
}

sealed interface AlbumDetailUiState {
    data object Loading : AlbumDetailUiState
    data class Success(
        val album: Album,
        val tracks: List<Track>
    ) : AlbumDetailUiState
    data class Error(val message: String) : AlbumDetailUiState
}
