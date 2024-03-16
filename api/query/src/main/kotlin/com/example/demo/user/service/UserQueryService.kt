package com.example.demo.user.service

import com.example.demo.service.UserDomainQueryService
import com.example.demo.user.dto.UserDto
import org.springframework.stereotype.Service

@Service
class UserQueryService(
    private val userDomainQueryService: UserDomainQueryService,
) {
    fun getUser(userId: Long): UserDto? {
        return userDomainQueryService.getUser(userId).let {
            UserDto(
                id = it.id,
                name = it.name,
                age = it.age,
            )
        }
    }
}
