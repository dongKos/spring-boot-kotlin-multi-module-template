package com.example.demo.persistence

interface Identifiable<T> {
    val id: T?

    val identifier: T
        get() = id!!
}
