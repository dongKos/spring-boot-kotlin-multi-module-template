package com.example.demo.security

import com.example.demo.security.auth.jwt.JwtAuthorizationFilter
import com.example.demo.security.auth.jwt.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true,
)
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        corsConfig: CorsConfig,
        customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
        jwtService: JwtService,
        auth: AuthenticationConfiguration,
    ): SecurityFilterChain {
        http.formLogin().disable()

        http {
            csrf {
                disable()
            }
            httpBasic { disable() }

            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }

            authenticationManager = auth.authenticationManager

            addFilterAt<CorsFilter>(corsConfig.corsFilter())

            authorizeRequests {
                listOf("/api/v1/signup").forEach {
                    authorize(HttpMethod.POST, it, permitAll)
                }

                listOf(
                    "/health",
                    "/api/v1/login/**",
                    "/internal/**",
                    "/configuration/ui",
                    "/configuration/security",
                ).forEach { authorize(it, permitAll) }

                authorize(HttpMethod.GET, "/.well-known/**", permitAll)
                authorize(anyRequest, hasRole("ROLE_USER"))
            }

            exceptionHandling {
                authenticationEntryPoint = customAuthenticationEntryPoint
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JwtAuthorizationFilter(
                    authenticationManager,
                    jwtService,
                ),
            )
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(auth: AuthenticationConfiguration): AuthenticationManager = auth.authenticationManager
}
