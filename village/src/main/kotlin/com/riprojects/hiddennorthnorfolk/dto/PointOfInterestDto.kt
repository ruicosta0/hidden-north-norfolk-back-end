package com.riprojects.hiddennorthnorfolk.dto

import java.time.Instant

data class PointOfInterestDto(
    val id: String,
    val landmarkId: String, // ??join column
    val name: String,
    val description: String,
    val updatedAt: Instant,
    val sources: List<PoiSourceDto> = emptyList()
)
