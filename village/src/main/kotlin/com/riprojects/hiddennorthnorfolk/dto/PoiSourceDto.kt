package com.riprojects.hiddennorthnorfolk.dto

data class PoiSourceDto(
    val id: String,
    val referenceType: String,
    val title: String,
    val author: String?,
    val publisher: String?,
    val publicationYear: Int?,
    val url: String?,
    val sourceType: String?,
    val displayOrder: Int
)
