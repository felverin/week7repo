package com.fel.dokemon.data.remote

import com.fel.dokemon.data.dto.CardResponseDto
import com.fel.dokemon.data.dto.SingleCardResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DokemonApi {
    @GET("cards")
    suspend fun getCards(
        @Query("q") q: String = "name:charizard",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): CardResponseDto

    @GET("cards/{id}")
    suspend fun getCardDetail(
        @Path("id") id: String
    ): SingleCardResponseDto
}
