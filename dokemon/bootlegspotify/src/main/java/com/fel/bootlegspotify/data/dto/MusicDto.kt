package com.fel.bootlegspotify.data.dto

import com.fel.bootlegspotify.domain.model.Album
import com.fel.bootlegspotify.domain.model.Artist
import com.fel.bootlegspotify.domain.model.Track
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponseDto(
    @SerialName("artists") val artists: List<ArtistDto>? = null
)

@Serializable
data class ArtistDto(
    @SerialName("idArtist") val idArtist: String,
    @SerialName("strArtist") val strArtist: String,
    @SerialName("strGenre") val strGenre: String? = null,
    @SerialName("strBiographyEN") val strBiographyEN: String? = null,
    @SerialName("strArtistThumb") val strArtistThumb: String? = null,
    @SerialName("strArtistBanner") val strArtistBanner: String? = null
)

@Serializable
data class AlbumResponseDto(
    @SerialName("album") val album: List<AlbumDto>? = null
)

@Serializable
data class AlbumDto(
    @SerialName("idAlbum") val idAlbum: String,
    @SerialName("strAlbum") val strAlbum: String,
    @SerialName("strArtist") val strArtist: String? = null,
    @SerialName("intYearReleased") val intYearReleased: String? = null,
    @SerialName("strGenre") val strGenre: String? = null,
    @SerialName("strDescriptionEN") val strDescriptionEN: String? = null,
    @SerialName("strAlbumThumb") val strAlbumThumb: String? = null
)

@Serializable
data class TrackResponseDto(
    @SerialName("track") val track: List<TrackDto>? = null
)

@Serializable
data class TrackDto(
    @SerialName("idTrack") val idTrack: String,
    @SerialName("strTrack") val strTrack: String,
    @SerialName("intTrackNumber") val intTrackNumber: String? = null,
    @SerialName("intDuration") val intDuration: String? = null
)

fun ArtistDto.toDomain(): Artist = Artist(
    id = idArtist,
    name = strArtist,
    genre = strGenre ?: "Alternative Rock",
    biography = strBiographyEN ?: "No biography available.",
    thumbUrl = strArtistThumb,
    bannerUrl = strArtistBanner ?: strArtistThumb
)

fun AlbumDto.toDomain(): Album = Album(
    id = idAlbum,
    title = strAlbum,
    artistName = strArtist ?: "",
    releaseYear = intYearReleased ?: "2020",
    genre = strGenre ?: "Pop-Rock",
    description = strDescriptionEN ?: "No description available.",
    thumbUrl = strAlbumThumb
)

fun TrackDto.toDomain(): Track = Track(
    id = idTrack,
    trackNumber = intTrackNumber?.toIntOrNull() ?: 1,
    title = strTrack,
    durationMs = intDuration?.toLongOrNull()
)
