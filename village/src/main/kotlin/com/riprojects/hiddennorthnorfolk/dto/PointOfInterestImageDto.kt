package com.riprojects.hiddennorthnorfolk.dto

import java.time.Instant


data class PointOfInterestImageDto(
    val id: String,
    var url: String,
    var caption: String?,
    var poiId: String, // join column
    val updatedAt: Instant, // join column
)
