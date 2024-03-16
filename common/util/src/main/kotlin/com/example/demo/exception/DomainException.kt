package com.example.demo.exception

open class DomainException(
    val errorCode: Int? = null,
    message: String,
) : RuntimeException(message) {
    companion object {
        const val GENERAL_BAD_REQUEST = 10001
    }
}
