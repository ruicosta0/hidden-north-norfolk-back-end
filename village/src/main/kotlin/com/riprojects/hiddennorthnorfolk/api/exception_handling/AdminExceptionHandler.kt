package com.riprojects.hiddennorthnorfolk.api.exception_handling

import com.riprojects.hiddennorthnorfolk.domain.exception.LandmarkNotFoundException
import com.riprojects.hiddennorthnorfolk.domain.exception.PointOfInterestNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class AdminExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun onValidationException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<Map<String, Any>> {
        val errors = e.bindingResult.allErrors.map {
            it.defaultMessage ?: "Invalid value"
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                mapOf(
                    "code" to "VALIDATION_ERROR",
                    "errors" to errors
                )
            )
    }


    @ExceptionHandler(LandmarkNotFoundException::class)
    fun handleNotFound(
        e: LandmarkNotFoundException
        ): ResponseEntity<Map<String, String?>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                mapOf(
                    "code" to "LANDMARK_NOT_FOUND",
                    "message" to e.message
                )
            )
    }

    @ExceptionHandler(PointOfInterestNotFoundException::class)
    fun handleNotFound(
        e: PointOfInterestNotFoundException
    ): ResponseEntity<Map<String, String?>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                mapOf(
                    "code" to "POI_NOT_FOUND",
                    "message" to e.message
                )
            )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(e: MethodArgumentTypeMismatchException): ResponseEntity<Map<String, Any>> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                mapOf(
                    "code" to "INVALID_ID_FORMAT",
                    "message" to "Invalid UUID: ${e.value}"
                )
            )
    }
}