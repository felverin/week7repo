package com.fel.bootlegspotify.data.remote

import com.fel.bootlegspotify.data.dto.AlbumResponseDto
import com.fel.bootlegspotify.data.dto.ArtistResponseDto
import com.fel.bootlegspotify.data.dto.TrackResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET("search.php")
    suspend fun searchArtist(
        @Query("s") artistName: String
    ): ArtistResponseDto

    @GET("searchalbum.php")
    suspend fun searchAlbums(
        @Query("s") artistName: String
    ): AlbumResponseDto

    @GET("album.php")
    suspend fun getAlbumDetail(
        @Query("m") albumId: String
    ): AlbumResponseDto

    @GET("track.php")
    suspend fun getAlbumTracks(
        @Query("m") albumId: String
    ): TrackResponseDto
}
