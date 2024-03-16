package com.example.demo.security.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AuthDetail(
    val userId: Long,
    val email: String,
    grantedAuthorities: Set<GrantedAuthority?>,
) : User(email, NOT_INSERT_PASSWORD, grantedAuthorities) {
    companion object {
        private const val NOT_INSERT_PASSWORD = ""
    }
}
