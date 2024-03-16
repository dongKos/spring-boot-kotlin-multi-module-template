package com.example.demo.security.auth.jwt

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(authenticationManager: AuthenticationManager?, private val jwtService: JwtService) :
    BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
    ) {
        if (isPublic(request)) {
            chain.doFilter(request, response)
            return
        }
        // header가 있는지 확인
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        // header에 Authorization -> accessToken이 있는지 확인
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // TODO 토큰 인증 잘못되었을 경우 Sevlet 응답에 401을 넣을 수 있도록 추가해야함
            // 방법
            // 1.해당 필터 앞 예외 잡을 수 있는 filter 추가
            // 2. EntryPoint 생성 -> 예외를 AuthenticationException을 상속받도록 해야함
            val authenticationResult =
                jwtService.getAuthenticationResultByAccessToken(
                    authorizationHeader.substring("Bearer ".length),
                )
            val authDetail = authenticationResult.createDetail()
            val authenticationToken =
                UsernamePasswordAuthenticationToken(
                    authDetail,
                    "",
                    authDetail.authorities,
                )
            SecurityContextHolder.getContext().authentication = authenticationToken
            chain.doFilter(request, response)
            return
        }
        chain.doFilter(request, response)
    }

    private fun isPublic(request: HttpServletRequest): Boolean {
        return request.servletPath == "/user/refreshToken"
    }

    companion object {
        private val log = LoggerFactory.getLogger(JwtAuthorizationFilter::class.java)
    }
}
