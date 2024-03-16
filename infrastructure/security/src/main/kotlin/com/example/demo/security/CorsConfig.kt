package com.example.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        // 내 서버가 응답할때 json을 자바스크립트에서 처리할 수 있는지 설정
        config.allowCredentials = true

        // 모든 ip 응답 허용
        config.addAllowedOriginPattern("*")

        // 모든 header 응답 허용
        config.addAllowedHeader("*")

        // 모든 method 응답(post, get, put, delete) 허용
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}
