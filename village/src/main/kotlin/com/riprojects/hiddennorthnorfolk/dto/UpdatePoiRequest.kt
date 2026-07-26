package com.riprojects.hiddennorthnorfolk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpdatePoiRequest(
    @field:Size(min = 1, max = 40, message = "Point Of Interest name must be a max of 40 characters")
    val name: String? =null,
    @field:NotBlank("Please add a description")
    val description: String? = null,
//    @field:DecimalMin("-90.0","Latitude cannot be less than -90")
//    @field:DecimalMax("90.0", "Latitude cannot be greater than 90")
//    val latitude: Double? = null,
//    @field:DecimalMin("-180.0", "Longitude cannot be less than -180")
//    @field:DecimalMax("180.0", "Longitude cannot be greater than 180")
//    val longitude: Double? = null,
)