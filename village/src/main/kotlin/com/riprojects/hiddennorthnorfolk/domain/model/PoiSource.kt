package com.riprojects.hiddennorthnorfolk.domain.model

import java.util.UUID

data class PoiSource(
    val id: UUID,
    val poiId: UUID,
    val referenceType: String,
    val title: String,
    val author: String?,
    val publisher: String?,
    val publicationYear: Int?,
    val url: String?,
    val sourceType: String?,
    val displayOrder: Int
)
