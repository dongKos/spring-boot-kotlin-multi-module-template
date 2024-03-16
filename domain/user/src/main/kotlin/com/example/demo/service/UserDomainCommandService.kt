package com.example.demo.service

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class UserDomainCommandService(
    private val userRepository: UserRepository,
) {
    fun createUser(
        name: String,
        age: Long,
    ): Long {
        return userRepository.save(
            User(
                name = name,
                age = age,
            ),
        ).identifier
    }
}
