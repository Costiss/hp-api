package br.ufpr.hpapi.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: String = "",
    val name: String = "",
    @SerializedName("alternate_names") val alternateNames: List<String> = emptyList(),
    val species: String = "",
    val house: String = "",
    val image: String = ""
)
