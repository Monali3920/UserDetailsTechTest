package com.test.assignment.domain.entities

import com.google.gson.annotations.SerializedName

data class UserHitsDetaileResponse(
    @SerializedName("total"     ) var total     : Int?            = null,
    @SerializedName("totalHits" ) var totalHits : Int?            = null,
    @SerializedName("hits"      ) var hits      : ArrayList<Hits> = arrayListOf()

): java.io.Serializable
