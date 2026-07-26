package com.riprojects.hiddennorthnorfolk.infra.database.repositories

import com.riprojects.hiddennorthnorfolk.infra.database.entities.LandmarkEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LandmarkRepository: JpaRepository<LandmarkEntity, UUID> { //LandmarkId is primary key
    fun findByName(name: String): LandmarkEntity?
    // could do find by co-ords if someone selected a map marker?
     fun findLandmarkById(landmarkId: UUID): LandmarkEntity?
}