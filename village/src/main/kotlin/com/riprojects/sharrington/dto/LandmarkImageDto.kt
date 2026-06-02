package com.riprojects.sharrington.dto

import java.time.Instant


data class LandmarkImageDto(
    val id: String,
    var image_path: String,
    var caption: String?,
    var landmark: String,
    val updatedAt: Instant, // join column
)
