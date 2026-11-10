package com.fel.dokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fel.dokemon.data.repository.DokemonRepository
import com.fel.dokemon.domain.model.Dokemon
import com.fel.dokemon.domain.model.DokemonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DokemonViewModel(
    private val repository: DokemonRepository = com.fel.dokemon.di.ServiceLocator.repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DokemonUiState>(DokemonUiState.Loading)
    val uiState: StateFlow<DokemonUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("Charizard")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCard = MutableStateFlow<Dokemon?>(null)
    val selectedCard: StateFlow<Dokemon?> = _selectedCard.asStateFlow()

    init {
        searchCards("Charizard")
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchCards(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _uiState.value = DokemonUiState.Loading
            val result = repository.searchCards(query)
            result.fold(
                onSuccess = { cards ->
                    _uiState.value = DokemonUiState.Success(cards)
                },
                onFailure = { error ->
                    _uiState.value = DokemonUiState.Error(error.localizedMessage ?: "Connection Failed")
                }
            )
        }
    }

    fun selectCard(card: Dokemon?) {
        _selectedCard.value = card
    }

    fun triggerErrorState() {
        _uiState.value = DokemonUiState.Error("HTTP 401")
    }
}
