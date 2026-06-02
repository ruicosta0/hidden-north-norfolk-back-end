package com.riprojects.sharrington.domain.model

import java.time.Instant
import java.time.OffsetDateTime
import java.util.UUID

typealias LandmarkId = UUID

data class Landmark (
    val id: LandmarkId,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val updatedAt: Instant
)