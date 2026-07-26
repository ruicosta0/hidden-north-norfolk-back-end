package com.riprojects.hiddennorthnorfolk.domain.model

import java.time.Instant
import java.util.UUID

typealias PoiImageId = UUID

data class PointOfInterestImage(
    var id: PoiImageId,
    var imageUrl: String,
    var caption: String?,
    var poiId: PoiId,
    val updatedAt: Instant
)
