package com.fel.bootlegspotify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fel.bootlegspotify.data.repository.MusicRepository
import com.fel.bootlegspotify.domain.model.ArtistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtistViewModel(
    private val repository: MusicRepository = com.fel.bootlegspotify.di.ServiceLocator.repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ArtistUiState>(ArtistUiState.Loading)
    val uiState: StateFlow<ArtistUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("Coldplay")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        searchArtist("Coldplay")
    }

    fun searchArtist(name: String) {
        _searchQuery.value = name
        viewModelScope.launch {
            _uiState.value = ArtistUiState.Loading
            val result = repository.getArtistData(name)
            result.fold(
                onSuccess = { (artist, albums) ->
                    _uiState.value = ArtistUiState.Success(artist, albums)
                },
                onFailure = { error ->
                    _uiState.value = ArtistUiState.Error(error.localizedMessage ?: "Failed to load artist data")
                }
            )
        }
    }
}
