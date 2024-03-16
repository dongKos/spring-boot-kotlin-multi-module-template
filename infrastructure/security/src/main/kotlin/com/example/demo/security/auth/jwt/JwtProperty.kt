package com.example.demo.security.auth.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtProperty {
    var secret = ""
    var accessExpireTime: Duration = Duration.ofDays(1)
}
