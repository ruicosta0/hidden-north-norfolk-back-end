package com.riprojects.sharrington.dto

import java.time.Instant

data class PointOfInterestDto(
    val id: String,
    val landmark: String, // ??join column
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val updatedAt: Instant
)
