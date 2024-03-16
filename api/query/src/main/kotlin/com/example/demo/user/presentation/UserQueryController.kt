package com.example.demo.user.presentation

import com.example.demo.user.dto.UserDto
import com.example.demo.user.presentation.UserQueryController.Companion.USER_PATH
import com.example.demo.user.service.UserQueryFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [USER_PATH])
class UserQueryController(
    private val userQueryFacade: UserQueryFacade,
) {
    companion object {
        private const val API_PREFIX = "/api"
        private const val VERSION_PREFIX = "/v1"
        private const val SERVICE_NAME = "/users"
        const val USER_PATH = "$API_PREFIX$VERSION_PREFIX$SERVICE_NAME"
    }

    @GetMapping("/{userId}")
    fun getUser(
        @PathVariable userId: Long,
    ): ResponseEntity<UserDto?> = ResponseEntity.ok(userQueryFacade.getUser(userId))
}
