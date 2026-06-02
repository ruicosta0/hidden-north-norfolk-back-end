package com.riprojects.sharrington.infra.database.repositories

import com.riprojects.sharrington.domain.model.PoiImageId
import com.riprojects.sharrington.domain.model.LandmarkId
import com.riprojects.sharrington.domain.model.PoiId
import com.riprojects.sharrington.infra.database.entities.LandmarkImageEntity
import com.riprojects.sharrington.infra.database.entities.PointOfInterestImageEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PointOfInterestImageRepository: JpaRepository<PointOfInterestImageEntity, UUID> {
    fun findByPoi_Id(poiId: UUID): List<PointOfInterestImageEntity> // this is calling @JoinColumn(name = "poi_id")
}