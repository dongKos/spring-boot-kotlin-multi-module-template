package com.example.demo.signing.service.signup

import com.example.demo.exception.GeneralBadRequestException
import com.example.demo.model.User.LoginType
import com.example.demo.model.User.LoginType.PASSWORD
import com.example.demo.service.UserDomainCommandService
import com.example.demo.signing.dto.SignupCommand
import com.example.demo.signing.dto.SignupResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordSignupService(
    userDomainCommandService: UserDomainCommandService,
    private val encoder: BCryptPasswordEncoder,
) : SignupService(userDomainCommandService) {
    override fun signup(signupCommand: SignupCommand): SignupResponse {
        return with(signupCommand) {
            if (password == null) {
                throw GeneralBadRequestException(message = "Password is required")
            }
            userDomainCommandService.signupWithPassword(
                name = name,
                age = age,
                loginType = loginType,
                password = encoder.encode(password),
            )
        }.let(::SignupResponse)
    }

    override fun supports(): LoginType = PASSWORD
}
