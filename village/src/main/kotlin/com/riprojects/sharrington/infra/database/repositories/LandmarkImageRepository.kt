package com.riprojects.sharrington.infra.database.repositories

import com.riprojects.sharrington.domain.model.PoiImageId
import com.riprojects.sharrington.domain.model.LandmarkId
import com.riprojects.sharrington.domain.model.LandmarkImageId
import com.riprojects.sharrington.domain.model.PoiId
import com.riprojects.sharrington.infra.database.entities.LandmarkImageEntity
import jakarta.persistence.JoinColumn
import org.springframework.data.jpa.repository.JpaRepository

interface LandmarkImageRepository: JpaRepository<LandmarkImageEntity, LandmarkImageId> {
    fun findByLandmarkId(landmarkId: LandmarkId): List<LandmarkImageEntity> // this is calling   @ManyToOne @JoinColumn(name = "landmark_id")
}