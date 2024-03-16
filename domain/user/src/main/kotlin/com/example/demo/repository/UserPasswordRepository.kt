package com.example.demo.repository

import com.example.demo.model.UserPassword
import org.springframework.data.jpa.repository.JpaRepository

interface UserPasswordRepository : JpaRepository<UserPassword, Long> {
    fun findByUserId(userId: Long): UserPassword?
}
