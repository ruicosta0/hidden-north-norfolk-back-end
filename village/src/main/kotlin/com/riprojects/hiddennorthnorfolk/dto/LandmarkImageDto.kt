package com.riprojects.hiddennorthnorfolk.dto

import java.time.Instant


data class LandmarkImageDto(
    val id: String,
    var url: String,
    var caption: String?,
    var landmarkId: String,
    val updatedAt: Instant, // join column
)


//data class LandmarkImageDto(
//    val id: String,
//    var imageUrl: String,
//    var caption: String?,
//    var landmark: String,
//    val updatedAt: Instant, // join column
//)