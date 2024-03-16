package com.example.demo.advice

import com.example.demo.exception.GeneralBadRequestException
import com.example.demo.exception.ErrorResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    @ExceptionHandler(GeneralBadRequestException::class)
    fun handleBadRequestException(e: GeneralBadRequestException): ResponseEntity<ErrorResponse<Unit>> {
        log.error { "[ExceptionHandler] Bad request exception: ${e.message}" }
        e.printStackTrace()
        return ResponseEntity.status(BAD_REQUEST).body(
            ErrorResponse(
                code = e.errorCode,
                message = e.message,
                data = null
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleDomainException(e: Exception): ResponseEntity<ErrorResponse<Unit>> {
        log.error { "[ExceptionHandler] exception: ${e.message}" }
        e.printStackTrace()
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                message = e.message,
                data = null
            )
        )
    }
}
