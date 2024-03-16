package com.example.demo.exception

data class ErrorResponse<T>(
    val code: Int? = null,
    val message: String?,
    val data: T?
)
