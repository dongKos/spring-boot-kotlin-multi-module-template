package com.example.demo.signing.service.signup

import com.example.demo.model.User.LoginType
import com.example.demo.service.UserDomainCommandService
import com.example.demo.signing.dto.SignupCommand
import com.example.demo.signing.dto.SignupResponse

abstract class SignupService(
    protected val userDomainCommandService: UserDomainCommandService,
) {
    abstract fun signup(signupCommand: SignupCommand): SignupResponse

    abstract fun supports(): LoginType
}
