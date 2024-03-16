package com.example.demo.exception

open class GeneralBadRequestException(
    code: Int = GENERAL_BAD_REQUEST,
    message: String,
): DomainException(errorCode = code, message = message)
