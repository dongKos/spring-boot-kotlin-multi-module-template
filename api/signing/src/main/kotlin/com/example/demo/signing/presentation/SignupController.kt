package com.example.demo.signing.presentation

import com.example.demo.signing.dto.SignupCommand
import com.example.demo.signing.dto.SignupResponse
import com.example.demo.signing.presentation.SignupController.Companion.SIGNUP_PATH
import com.example.demo.signing.service.SigningFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(SIGNUP_PATH)
class SignupController(
    private val signingFacade: SigningFacade,
) {
    companion object {
        private const val API_PREFIX = "/api"
        private const val VERSION_PREFIX = "/v1"
        private const val SERVICE_NAME = "/signup"
        const val SIGNUP_PATH = "$API_PREFIX$VERSION_PREFIX$SERVICE_NAME"
    }

    @PostMapping
    fun signup(
        @RequestBody signupCommand: SignupCommand,
    ): ResponseEntity<SignupResponse> =
        ResponseEntity.ok(
            signingFacade.signup(signupCommand),
        )
}
