package com.riprojects.sharrington.infra.database.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "landmark",
    schema = "landmark_service",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["latitude", "longitude"])
    ] ,
    indexes = [
        Index(name="idx_landmark_name", columnList = "name"),
        Index(name = "idx_landmark_lat_lon", columnList = "latitude,longitude")
    ]
)

// note jpa is java not Kotlin hence var and not val, class and not data class, nullable instead of ?
class LandmarkEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null, // jpa requires the ID field to be nullable because it starts as null before persistence
    @Column(nullable = false,unique = false)
    var name: String,
    @Column(nullable = false,unique = false)
    var description: String,
    @Column(nullable = false, unique = false)
    var latitude: Double,
    @Column(nullable = false, unique = false)
    var longitude: Double,
    @OneToOne(mappedBy = "landmark", cascade =[CascadeType.ALL], orphanRemoval = true)
    var poi: PointOfInterestEntity? = null,
    @OneToMany(mappedBy = "landmark", cascade =[CascadeType.ALL], orphanRemoval = true)
    var images: MutableList<LandmarkImageEntity> = mutableListOf(),
    @CreationTimestamp
    val createdAt: Instant = Instant.now(),
    @UpdateTimestamp
    val updatedAt: Instant? = null
)