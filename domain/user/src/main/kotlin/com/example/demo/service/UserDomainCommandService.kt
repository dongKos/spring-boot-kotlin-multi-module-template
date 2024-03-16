package com.example.demo.service

import com.example.demo.model.User
import com.example.demo.model.User.LoginType
import com.example.demo.model.UserPassword
import com.example.demo.repository.UserPasswordRepository
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class UserDomainCommandService(
    private val userRepository: UserRepository,
    private val userPasswordRepository: UserPasswordRepository,
) {
    fun signupWithPassword(
        name: String,
        age: Long,
        loginType: LoginType,
        password: String,
    ): Long {
        val user =
            userRepository.save(
                User(
                    name = name,
                    age = age,
                    loginType = loginType,
                ),
            ).also {
                userPasswordRepository.save(
                    UserPassword(
                        userId = it.identifier,
                        password = password,
                    ),
                )
            }

        return user.identifier
    }
}
