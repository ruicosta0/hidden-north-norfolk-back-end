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
