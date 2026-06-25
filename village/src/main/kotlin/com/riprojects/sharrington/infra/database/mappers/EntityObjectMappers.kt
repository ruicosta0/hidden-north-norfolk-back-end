package com.riprojects.sharrington.infra.database.mappers

import com.riprojects.sharrington.domain.model.PointOfInterestImage
import com.riprojects.sharrington.domain.model.Landmark
import com.riprojects.sharrington.domain.model.LandmarkImage
import com.riprojects.sharrington.domain.model.PointOfInterest
import com.riprojects.sharrington.infra.database.entities.LandmarkImageEntity
import com.riprojects.sharrington.infra.database.entities.LandmarkEntity
import com.riprojects.sharrington.infra.database.entities.PointOfInterestImageEntity
import com.riprojects.sharrington.infra.database.entities.PointOfInterestEntity

fun LandmarkEntity.toLandmark(): Landmark {
    return Landmark(
        id = id!!,
        name = name,
        description =  description,
        latitude = latitude,
        longitude = longitude,
        updatedAt = updatedAt!!
    )
}

fun PointOfInterestEntity.toPointOfInterest(): PointOfInterest {
    return PointOfInterest(
        id = id!!,
        landmarkId = landmark.id!!,
        name =  name,
        description = description,
        updatedAt = updatedAt!!
    )
}

fun LandmarkImageEntity.toLandmarkImage(): LandmarkImage {
    return LandmarkImage(
        id = id!!,
        imageUrl = image_path,
        caption = caption,
        landmarkId = landmark.id!!,
        updatedAt = updatedAt!!
    )
}

fun PointOfInterestImageEntity.toPoiImage(): PointOfInterestImage {
    return PointOfInterestImage(
        id = id!!,
        image_path = image_path,
        caption = caption,
        poiId = poi.id!!,
        updatedAt = updatedAt!!
    )
}

