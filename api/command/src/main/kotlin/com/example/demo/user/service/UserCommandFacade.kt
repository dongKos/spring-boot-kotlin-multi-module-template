package com.example.demo.user.service

import com.example.demo.service.UserDomainCommandService
import com.example.demo.user.dto.CreateUserCommand
import com.example.demo.user.dto.CreateUserResponse
import org.springframework.stereotype.Service

@Service
class UserCommandFacade(
    private val userDomainCommandService: UserDomainCommandService,
) {
    fun createUser(createUserCommand: CreateUserCommand): CreateUserResponse {
        return with(createUserCommand) {
            userDomainCommandService.createUser(
                name = name,
                age = age,
            )
        }.let(::CreateUserResponse)
    }
}
