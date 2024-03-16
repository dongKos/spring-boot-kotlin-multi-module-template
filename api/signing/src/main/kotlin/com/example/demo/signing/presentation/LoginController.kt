package com.example.demo.signing.presentation

import com.example.demo.signing.dto.LoginRequest
import com.example.demo.signing.dto.LoginResponse
import com.example.demo.signing.presentation.LoginController.Companion.LOGIN_PATH
import com.example.demo.signing.service.SigningFacade
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Service
@RequestMapping(LOGIN_PATH)
class LoginController(
    private val signingFacade: SigningFacade,
) {
    companion object {
        private const val API_PREFIX = "/api"
        private const val VERSION_PREFIX = "/v1"
        private const val SERVICE_NAME = "/login"
        const val LOGIN_PATH = "$API_PREFIX$VERSION_PREFIX$SERVICE_NAME"
    }

    @Operation(
        operationId = "LOGIN",
        summary = "로그인",
        tags = ["signing"],
        description = "로그인",
    )
    @PostMapping
    fun login(
        @RequestBody loginRequest: LoginRequest,
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(signingFacade.login(loginRequest))
    }
}
