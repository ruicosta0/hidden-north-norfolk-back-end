package com.riprojects.sharrington.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class RecordPoiRequest @JsonCreator constructor(
    @JsonProperty("name")
    @field:Size(min = 1, max = 40, message = "Landmark name must be a max of 40 characters")
    val name: String,
    @JsonProperty ("description")
    @field:NotBlank("Please add a description")
    val description: String,
//    @JsonProperty("latitude")
//    @field:DecimalMin("-90.0","Latitude cannot be less than -90")
//    @field:DecimalMax("90.0", "Latitude cannot be greater than 90")
//    val latitude: Double,
//    @JsonProperty("longitude")
//    @field:DecimalMin("-180.0", "Longitude cannot be less than -180")
//    @field:DecimalMax("180.0", "Longitude cannot be greater than 180")
//    val longitude: Double,
)