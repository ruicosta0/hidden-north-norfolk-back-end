package com.riprojects.sharrington

import com.riprojects.sharrington.infra.database.entities.LandmarkEntity
import com.riprojects.sharrington.infra.database.repositories.LandmarkRepository
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class SharringtonApplication

fun main(args: Array<String>) {
	runApplication<SharringtonApplication>(*args)
}

/*@Component
class Demo(
    private val repository: LandmarkRepository
) {

    @PostConstruct
    fun init() {
        repository.saveAll(
            listOf(
                LandmarkEntity(
                    name = "The Old Chapel",
                    description = "Former Methodist chapel, now a holiday home",
                    latitude = 52.89187267591712,
                    longitude = 1.024224990009409
                ),
                LandmarkEntity(
                    name = "Sharrington Village Hall",
                    description = "The village's hall built in 1953",
                    latitude = 52.89341434411429,
                    longitude = 1.0239539879302006
                ),
                LandmarkEntity(
                    name = "Village Pond",
                    description = "Pond with ducks and moor hens",
                    latitude = 52.893882597749425,
                    longitude = 1.0179605887771752
                ),
                LandmarkEntity(
                    name = "Sharrington Village Sign",
                    description = "Village sign, rebus of barrel and bees",
                    latitude = 52.8922941528462,
                    longitude =  1.0189914643967677
                ),
                LandmarkEntity(
                    name = "Pilgrim's Post",
                    description = "Medieval waymarker for Walsingham",
                    latitude =  52.889171311387805,
                    longitude = 1.0178810362186939
                ),
                LandmarkEntity(
                    name = "Phone Box",
                    description = "Famous red KC phone booth",
                    latitude = 52.88943671738154,
                    longitude =  1.0212337974491466
                ),
                LandmarkEntity(
                    name = "Sharrington Hall",
                    description = "Jacobean Hall",
                    latitude = 52.89202036593169,
                    longitude = 1.0188317971860785
                )
            )
        )
    }
}*/
