package com.example.demo.security.auth

import com.auth0.jwt.interfaces.DecodedJWT
import com.example.demo.security.auth.AuthData.USER_ID
import com.example.demo.security.auth.AuthData.USER_NAME
import com.example.demo.security.auth.AuthData.USER_ROLES
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AuthenticationResult private constructor(decodedJWT: DecodedJWT) {
    private val decodedJWT: DecodedJWT

    init {
        this.decodedJWT = decodedJWT
    }

    fun createDetail(): AuthDetail {
        return AuthDetail(
            decodedJWT.getClaim(USER_ID).asLong(),
            decodedJWT.getClaim(USER_NAME).asString(),
            extractGrantAuthorities(),
        )
    }

    private fun extractGrantAuthorities(): Set<GrantedAuthority?> {
        return decodedJWT.getClaim(USER_ROLES).asArray(String::class.java)
            .map { SimpleGrantedAuthority(it) }
            .toSet()
    }

    companion object {
        fun of(decodedJWT: DecodedJWT?): AuthenticationResult {
            requireNotNull(decodedJWT) {
                // TODO: 추후 전체적 예외 구조 변경 및 확정후 변경 401 응답 반환 필요
                "잘못된 access token 요청입니다."
            }
            return AuthenticationResult(decodedJWT)
        }
    }
}
