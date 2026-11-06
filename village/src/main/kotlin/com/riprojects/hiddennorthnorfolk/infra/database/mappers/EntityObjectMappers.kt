package com.riprojects.hiddennorthnorfolk.infra.database.mappers

import com.riprojects.hiddennorthnorfolk.domain.model.PointOfInterestImage
import com.riprojects.hiddennorthnorfolk.domain.model.Landmark
import com.riprojects.hiddennorthnorfolk.domain.model.LandmarkImage
import com.riprojects.hiddennorthnorfolk.domain.model.PoiSource
import com.riprojects.hiddennorthnorfolk.domain.model.PointOfInterest
import com.riprojects.hiddennorthnorfolk.infra.database.entities.LandmarkImageEntity
import com.riprojects.hiddennorthnorfolk.infra.database.entities.LandmarkEntity
import com.riprojects.hiddennorthnorfolk.infra.database.entities.PoiSourceEntity
import com.riprojects.hiddennorthnorfolk.infra.database.entities.PointOfInterestImageEntity
import com.riprojects.hiddennorthnorfolk.infra.database.entities.PointOfInterestEntity

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
        imageUrl = image_path,
        caption = caption,
        poiId = poi.id!!,
        updatedAt = updatedAt!!
    )
}

fun PoiSourceEntity.toPoiSource(): PoiSource {
    return PoiSource(
        id = requireNotNull(id),
        poiId = poiId,
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

