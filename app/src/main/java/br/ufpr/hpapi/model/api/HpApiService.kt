package br.ufpr.hpapi.model.api

import br.ufpr.hpapi.model.Character
import br.ufpr.hpapi.model.Spell
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {

    @GET("api/characters")
    suspend fun getAllCharacters(): List<Character>

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<Character>

    @GET("api/characters/staff")
    suspend fun getAllStaff(): List<Character>

    @GET("api/characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): List<Character>

    @GET("api/spells")
    suspend fun getAllSpells(): List<Spell>
}
