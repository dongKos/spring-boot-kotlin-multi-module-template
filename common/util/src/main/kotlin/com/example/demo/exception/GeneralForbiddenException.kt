package com.example.demo.exception

open class GeneralForbiddenException(
    code: Int = GENERAL_BAD_REQUEST,
    message: String,
) : DomainException(errorCode = code, message = message)
