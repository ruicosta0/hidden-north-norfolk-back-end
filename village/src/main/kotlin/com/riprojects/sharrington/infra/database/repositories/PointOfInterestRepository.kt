package com.riprojects.sharrington.infra.database.repositories

import com.riprojects.sharrington.infra.database.entities.PointOfInterestEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PointOfInterestRepository: JpaRepository<PointOfInterestEntity, UUID> { //PoiId is primary key from domain model
    fun findByLandmark_Id(landmarkId: UUID): PointOfInterestEntity?
}