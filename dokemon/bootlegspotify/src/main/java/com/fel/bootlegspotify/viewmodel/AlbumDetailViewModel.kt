package com.fel.bootlegspotify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fel.bootlegspotify.data.repository.MusicRepository
import com.fel.bootlegspotify.domain.model.AlbumDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumDetailViewModel(
    private val albumId: String,
    private val repository: MusicRepository = com.fel.bootlegspotify.di.ServiceLocator.repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AlbumDetailUiState>(AlbumDetailUiState.Loading)
    val uiState: StateFlow<AlbumDetailUiState> = _uiState.asStateFlow()

    init {
        loadAlbumDetail()
    }

    fun loadAlbumDetail() {
        viewModelScope.launch {
            _uiState.value = AlbumDetailUiState.Loading
            val result = repository.getAlbumDetailAndTracks(albumId)
            result.fold(
                onSuccess = { (album, tracks) ->
                    _uiState.value = AlbumDetailUiState.Success(album, tracks)
                },
                onFailure = { error ->
                    _uiState.value = AlbumDetailUiState.Error(error.localizedMessage ?: "Failed to load album details")
                }
            )
        }
    }
}
