package com.riprojects.hiddennorthnorfolk.dto

import java.time.Instant


// sent back from server to the client
data class LandmarkDto(
    val id: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val updatedAt: Instant
)
