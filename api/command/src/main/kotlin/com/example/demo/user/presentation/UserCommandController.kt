package com.example.demo.user.presentation

import com.example.demo.user.dto.CreateUserCommand
import com.example.demo.user.dto.CreateUserResponse
import com.example.demo.user.presentation.UserCommandController.Companion.USER_PATH
import com.example.demo.user.service.UserCommandFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(USER_PATH)
class UserCommandController(
    private val userCommandFacade: UserCommandFacade,
) {
    companion object {
        private const val API_PREFIX = "/api"
        private const val VERSION_PREFIX = "/v1"
        private const val SERVICE_NAME = "/users"
        const val USER_PATH = "$API_PREFIX$VERSION_PREFIX$SERVICE_NAME"
    }

    @PostMapping
    fun createUser(
        @RequestBody createUserCommand: CreateUserCommand,
    ): ResponseEntity<CreateUserResponse> =
        ResponseEntity.ok(
            userCommandFacade.createUser(createUserCommand),
        )
}
