package com.example.demo.service

import com.example.demo.dto.UserInfo
import com.example.demo.exception.UserNotFoundException
import com.example.demo.model.User
import com.example.demo.repository.UserPasswordRepository
import com.example.demo.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserDomainQueryService(
    private val userRepository: UserRepository,
    private val userPasswordRepository: UserPasswordRepository,
) {
    protected fun findByIdOrThrow(userId: Long): User {
        return userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException(userId)
    }

    fun getUser(userId: Long): UserInfo {
        val userPassword = userPasswordRepository.findByUserId(userId)
        return this.findByIdOrThrow(userId).let {
            UserInfo(
                id = it.identifier,
                name = it.name,
                age = it.age,
                password = userPassword?.password,
                roles = it.getRoleList(),
            )
        }
    }
}
