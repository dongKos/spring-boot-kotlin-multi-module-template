package com.example.demo.filter

import com.example.demo.security.auth.jwt.JwtService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MDCFilterConfig(
    private val jwtService: JwtService,
) {
    @Bean
    fun mdcFilter(): FilterRegistrationBean<MDCFilter> =
        FilterRegistrationBean(MDCFilter(jwtService))
            .apply { order = Integer.MAX_VALUE }
}
