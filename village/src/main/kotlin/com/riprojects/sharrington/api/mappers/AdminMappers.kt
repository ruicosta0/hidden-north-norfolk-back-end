package com.riprojects.sharrington.api.mappers

import com.riprojects.sharrington.domain.model.Landmark
import com.riprojects.sharrington.domain.model.LandmarkImage
import com.riprojects.sharrington.domain.model.PointOfInterest
import com.riprojects.sharrington.domain.model.PointOfInterestImage
import com.riprojects.sharrington.dto.LandmarkImageDto
import com.riprojects.sharrington.dto.LandmarkDto
import com.riprojects.sharrington.dto.PointOfInterestImageDto
import com.riprojects.sharrington.dto.PointOfInterestDto



// map domain objects to dto objects
fun Landmark.toLandmarkDto(): LandmarkDto {
    return LandmarkDto(
        id = id.toString(),
        name = name,
        description = description,
        latitude = latitude,
        longitude = longitude,
        updatedAt = updatedAt
    )
}

fun PointOfInterest.toPointOfInterestDto(): PointOfInterestDto {
    return PointOfInterestDto(
        id = id.toString(),
        landmarkId = landmarkId.toString(),
        name = name,
        description = description,
        updatedAt = updatedAt
    )
}

fun LandmarkImage.toLandmarkImageDto(): LandmarkImageDto {
    return LandmarkImageDto(
        id = id.toString(),
        url = imageUrl,
        caption = caption,
        landmarkId = landmarkId.toString(),
        updatedAt = updatedAt
    )
}

fun PointOfInterestImage.toPointOfInterestImageDto(): PointOfInterestImageDto {
    return PointOfInterestImageDto(
        id = id.toString(),
        url = imageUrl,
        caption = caption,
        poiId = poiId.toString(),
        updatedAt = updatedAt
    )
}