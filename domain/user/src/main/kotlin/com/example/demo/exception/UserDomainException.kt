package com.example.demo.exception

open class UserDomainException(
    override val message: String
): DomainException(message = message)
