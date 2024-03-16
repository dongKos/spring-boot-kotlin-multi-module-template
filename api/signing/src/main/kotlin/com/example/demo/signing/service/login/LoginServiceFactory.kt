package com.example.demo.signing.service.login

import com.example.demo.model.User.LoginType
import org.springframework.stereotype.Service

@Service
class LoginServiceFactory(
    private val loginServices: List<LoginService>,
) {
    private val loginServiceMap: Map<LoginType, LoginService> = loginServices.associateBy { it.supports() }

    fun getInstance(loginType: LoginType): LoginService =
        loginServiceMap[loginType] ?: throw IllegalArgumentException("Unsupported login type: $loginType")
}
