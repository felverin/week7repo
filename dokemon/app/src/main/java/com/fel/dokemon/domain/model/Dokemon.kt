package com.fel.dokemon.domain.model

data class Dokemon(
    val id: String,
    val name: String,
    val cardNumber: String,
    val setName: String,
    val imageUrl: String,
    val largeImageUrl: String,
    val marketPrice: Double?,
    val lowestPrice: Double?,
    val rarity: String,
    val types: List<String>,
    val illustrator: String?,
    val activeListings: Int?,
    val verifiedSellers: Int?,
    val primaryPrinting: String?,
    val isOversized: Boolean = false
)

sealed interface DokemonUiState {
    data object Loading : DokemonUiState
    data class Success(val cards: List<Dokemon>) : DokemonUiState
    data class Error(val message: String) : DokemonUiState
}
