package com.example.demo.security.auth.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.demo.exception.GeneralForbiddenException
import com.example.demo.security.auth.AuthData.USER_ID
import com.example.demo.security.auth.AuthData.USER_NAME
import com.example.demo.security.auth.AuthData.USER_ROLES
import com.example.demo.security.auth.AuthenticationResult
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.sql.Date

@Service
class JwtService(
    private val jwtProperty: JwtProperty,
) {
    companion object {
        val log = KotlinLogging.logger {}
    }

    fun generateAccessToken(
        name: String,
        userId: Long,
        roles: List<String>,
        issuer: String = "SYSTEM",
    ): String {
        return JWT.create().withSubject(name)
            .withExpiresAt(Date(System.currentTimeMillis() + this.jwtProperty.accessExpireTime.toMillis()))
            .withIssuer(issuer)
            .withClaim(USER_NAME, name)
            .withClaim(USER_ID, userId)
            .withClaim(USER_ROLES, roles).sign(Algorithm.HMAC512(this.jwtProperty.secret))
    }

    fun getAuthenticationResultByAccessToken(accessToken: String): AuthenticationResult {
        return try {
            val decodedJwt =
                JWT.require(
                    Algorithm.HMAC512(this.jwtProperty.secret),
                ).build()
                    .verify(accessToken)
            AuthenticationResult.of(decodedJwt)
        } catch (e: JWTVerificationException) {
            throw GeneralForbiddenException(message = "Invalid Access Token")
        }
    }

    fun verifyAndGetDecodedTokenOrThrow(jwtToken: String?): DecodedJWT {
        return try {
            JWT.require(Algorithm.HMAC512(this.jwtProperty.secret)).build().verify(jwtToken)
        } catch (e: JWTVerificationException) {
            throw RuntimeException("")
        }
    }

    fun getDecodedJwtFromAuthenticationHeader(authenticationHeader: String): DecodedJWT? {
        return try {
            val jwtToken = authenticationHeader.replace("Bearer ", "")
            this.verifyAndGetDecodedTokenOrThrow(jwtToken)
        } catch (e: JWTVerificationException) {
            null
        }
    }
}
