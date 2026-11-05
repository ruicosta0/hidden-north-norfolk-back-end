package com.riprojects.hiddennorthnorfolk.infra.database.repositories

import com.riprojects.hiddennorthnorfolk.infra.database.entities.PoiSourceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PoiSourceRepository : JpaRepository<PoiSourceEntity, UUID> {

    fun findByPoiId(poiId: UUID): List<PoiSourceEntity>

}