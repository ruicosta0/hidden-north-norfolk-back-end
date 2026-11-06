package com.riprojects.hiddennorthnorfolk.infra.database.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "poi_sources",
    schema = "landmark_service"
)
class PoiSourceEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(name = "poi_id", nullable = false)
    var poiId: UUID,

    @Column(name = "reference_type", nullable = false)
    var referenceType: String,

    @Column(nullable = false)
    var title: String,

    var author: String? = null,

    var publisher: String? = null,

    @Column(name = "publication_year")
    var publicationYear: Int? = null,

    var url: String? = null,

    @Column(name = "source_type")
    var sourceType: String? = null,

    @Column(name = "display_order", nullable = false)
    var displayOrder: Int = 0
)