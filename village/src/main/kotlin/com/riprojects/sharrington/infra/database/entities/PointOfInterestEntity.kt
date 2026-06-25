package com.riprojects.sharrington.infra.database.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "pointOfInterest",
    schema = "landmark_service",
    indexes = [
        Index(name="idx_poi_name", columnList = "name"),
    ]
)

// note jpa is java not Kotlin hence var and not val, class and not data class, nullable instead of ?
class PointOfInterestEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null, // jpa requires the ID field to be nullable because it starts as null before persistence
    @ManyToOne
    @JoinColumn(name = "landmark_id")
    val landmark: LandmarkEntity,
    @Column(nullable = false,unique = false)
    var name: String,
    @Column(nullable = false,unique = false, columnDefinition = "Text")
    var description: String,
    @Column(nullable = false)

    @OneToMany(mappedBy = "poi",cascade =[CascadeType.ALL], orphanRemoval = true)
    val images: MutableList<PointOfInterestImageEntity> = mutableListOf(),
    @CreationTimestamp
    val createdAt: Instant = Instant.now(),
    @UpdateTimestamp
    val updatedAt: Instant? = null,
)