package com.riprojects.hiddennorthnorfolk.infra.database.repositories

import com.riprojects.hiddennorthnorfolk.domain.model.LandmarkImageId
import com.riprojects.hiddennorthnorfolk.infra.database.entities.LandmarkImageEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LandmarkImageRepository: JpaRepository<LandmarkImageEntity, LandmarkImageId> {
    //fun findByLandmarkId(landmarkId: LandmarkId): List<LandmarkImageEntity> // this is calling   @ManyToOne @JoinColumn(name = "landmark_id")
    fun findAllByLandmark_Id(landmarkId: UUID): List<LandmarkImageEntity>
}



