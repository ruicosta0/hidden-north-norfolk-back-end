package com.riprojects.sharrington.dto

import java.time.Instant


data class PointOfInterestImageDto(
    val id: String,
    var imageUrl: String,
    var caption: String?,
    var poi: String, // join column
    val updatedAt: Instant, // join column
)
