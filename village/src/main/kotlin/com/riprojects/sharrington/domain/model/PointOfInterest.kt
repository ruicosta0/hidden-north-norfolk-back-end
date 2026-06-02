package com.riprojects.sharrington.domain.model

import com.riprojects.sharrington.infra.database.entities.LandmarkEntity
import java.time.Instant
import java.util.UUID

typealias PoiId = UUID

data class PointOfInterest(
    val id: PoiId,
    val landmarkId: LandmarkId, // join column
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val updatedAt: Instant
)