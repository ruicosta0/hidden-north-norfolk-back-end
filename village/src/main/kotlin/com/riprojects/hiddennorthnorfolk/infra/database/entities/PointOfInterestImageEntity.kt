package com.riprojects.hiddennorthnorfolk.infra.database.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "poi_image",
    schema = "landmark_service",
    indexes = [
        Index(name="idx_image_poi_id", columnList = "poi_id"),
    ]
)

class PointOfInterestImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null, // jpa requires the ID field to be nullable because it starts as null before persistence

    var image_path: String,

    var caption: String? = null,


    @ManyToOne
    @JoinColumn(name = "poi_id")
    val poi: PointOfInterestEntity,

    @CreationTimestamp
    val createdAt: Instant = Instant.now(),
    @UpdateTimestamp
    val updatedAt: Instant? = null,

)