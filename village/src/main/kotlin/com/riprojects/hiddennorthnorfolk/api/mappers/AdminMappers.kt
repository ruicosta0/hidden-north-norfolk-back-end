package com.riprojects.hiddennorthnorfolk.api.mappers

import com.riprojects.hiddennorthnorfolk.domain.model.Landmark
import com.riprojects.hiddennorthnorfolk.domain.model.LandmarkImage
import com.riprojects.hiddennorthnorfolk.domain.model.PoiSource
import com.riprojects.hiddennorthnorfolk.domain.model.PointOfInterest
import com.riprojects.hiddennorthnorfolk.domain.model.PointOfInterestImage
import com.riprojects.hiddennorthnorfolk.dto.LandmarkImageDto
import com.riprojects.hiddennorthnorfolk.dto.LandmarkDto
import com.riprojects.hiddennorthnorfolk.dto.PoiSourceDto
import com.riprojects.hiddennorthnorfolk.dto.PointOfInterestImageDto
import com.riprojects.hiddennorthnorfolk.dto.PointOfInterestDto



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

fun PoiSource.toPoiSourceDto(): PoiSourceDto {
    return PoiSourceDto(
        id = id.toString(),
        referenceType = referenceType,
        title = title,
        author = author,
        publisher = publisher,
        publicationYear = publicationYear,
        url = url,
        sourceType = sourceType,
        displayOrder = displayOrder
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