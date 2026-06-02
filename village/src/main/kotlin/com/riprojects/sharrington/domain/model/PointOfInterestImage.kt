package com.riprojects.sharrington.domain.model

import java.time.Instant
import java.util.UUID

typealias PoiImageId = UUID

data class PointOfInterestImage(
    var id: PoiImageId,
    var image_path: String,
    var caption: String?,
    var poiId: PoiId,
    val updatedAt: Instant
)
