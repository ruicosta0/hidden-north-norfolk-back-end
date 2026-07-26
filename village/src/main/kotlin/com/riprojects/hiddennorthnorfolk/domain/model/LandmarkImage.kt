package com.riprojects.hiddennorthnorfolk.domain.model

import java.time.Instant
import java.util.UUID

typealias LandmarkImageId = UUID

data class LandmarkImage(
    var id: LandmarkImageId,
    var imageUrl: String,
    var caption: String?,
    var landmarkId: LandmarkId,
    val updatedAt: Instant
)
