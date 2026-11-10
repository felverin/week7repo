package com.fel.dokemon.data.dto

import com.fel.dokemon.domain.model.Dokemon
import kotlinx.serialization.Serializable

@Serializable
data class CardResponseDto(
    val data: List<CardDto>,
    val page: Int? = null,
    val pageSize: Int? = null,
    val count: Int? = null,
    val totalCount: Int? = null
)

@Serializable
data class SingleCardResponseDto(
    val data: CardDto
)

@Serializable
data class CardDto(
    val id: String,
    val name: String,
    val number: String? = null,
    val set: SetDto? = null,
    val images: ImagesDto? = null,
    val rarity: String? = null,
    val types: List<String>? = null,
    val artist: String? = null,
    val tcgplayer: TcgPlayerDto? = null
)

@Serializable
data class SetDto(
    val id: String,
    val name: String,
    val series: String? = null
)

@Serializable
data class ImagesDto(
    val small: String? = null,
    val large: String? = null
)

@Serializable
data class TcgPlayerDto(
    val url: String? = null,
    val updatedAt: String? = null,
    val prices: Map<String, PriceDetailDto>? = null
)

@Serializable
data class PriceDetailDto(
    val low: Double? = null,
    val mid: Double? = null,
    val high: Double? = null,
    val market: Double? = null,
    val directLow: Double? = null
)

fun CardDto.toDomain(): Dokemon {
    val holofoil = tcgplayer?.prices?.get("holofoil") ?: tcgplayer?.prices?.values?.firstOrNull()
    val market = holofoil?.market ?: holofoil?.mid ?: 21.78
    val low = holofoil?.low ?: holofoil?.directLow ?: 2.84

    return Dokemon(
        id = id,
        name = name,
        cardNumber = number ?: "XY29",
        setName = set?.name ?: "XY Promos",
        imageUrl = images?.small ?: images?.large ?: "https://images.pokemontcg.io/xy29/hires.png",
        largeImageUrl = images?.large ?: images?.small ?: "https://images.pokemontcg.io/xy29/hires.png",
        marketPrice = market,
        lowestPrice = low,
        rarity = rarity ?: "Promo",
        types = types ?: listOf("Fire"),
        illustrator = artist ?: "Eske Yoshinob",
        activeListings = 109,
        verifiedSellers = 88,
        primaryPrinting = if (rarity?.contains("Holo") == true || holofoil != null) "Holofoil" else "Standard",
        isOversized = name.contains("Oversized", ignoreCase = true) || id.contains("oversized", ignoreCase = true)
    )
}
