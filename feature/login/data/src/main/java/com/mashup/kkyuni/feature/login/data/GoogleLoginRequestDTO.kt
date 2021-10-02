package com.mashup.kkyuni.feature.login.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoogleLoginRequestDTO(
    @Json(name = "idToken") val idToken: String
)