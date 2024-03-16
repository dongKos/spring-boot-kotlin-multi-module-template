package com.example.demo.signing.service.signup

import com.example.demo.model.User.LoginType
import org.springframework.stereotype.Service

@Service
class SignupServiceFactory(
    private val signupServices: List<SignupService>,
) {
    private val signupServiceMap: Map<LoginType, SignupService> = signupServices.associateBy { it.supports() }

    fun getInstance(loginType: LoginType): SignupService =
        signupServiceMap[loginType] ?: throw IllegalArgumentException("Unsupported login type: $loginType")
}
