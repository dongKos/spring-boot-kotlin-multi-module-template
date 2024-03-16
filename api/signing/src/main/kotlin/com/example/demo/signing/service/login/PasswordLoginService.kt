package com.example.demo.signing.service.login

import com.example.demo.dto.UserInfo
import com.example.demo.model.User.LoginType
import com.example.demo.model.User.LoginType.PASSWORD
import com.example.demo.security.auth.jwt.JwtService
import com.example.demo.service.UserDomainQueryService
import com.example.demo.signing.dto.LoginRequest
import com.example.demo.signing.dto.LoginResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordLoginService(
    private val userDomainQueryService: UserDomainQueryService,
    private val jwtService: JwtService,
    private val encoder: BCryptPasswordEncoder,
) : LoginService {
    override fun login(loginRequest: LoginRequest): LoginResponse {
        val userInfo = userDomainQueryService.getUser(loginRequest.userId)
        validatePassword(loginRequest, userInfo)
        return LoginResponse(
            userId = userInfo.id,
            accessToken =
                jwtService.generateAccessToken(
                    name = userInfo.name,
                    userId = userInfo.id,
                    roles = userInfo.roles,
                ),
        )
    }

    private fun validatePassword(
        loginRequest: LoginRequest,
        userInfo: UserInfo,
    ) {
        encoder.matches(loginRequest.password, userInfo.password)
            .takeIf { it }
            ?: throw IllegalArgumentException("Invalid password")
    }

    override fun supports(): LoginType = PASSWORD
}
