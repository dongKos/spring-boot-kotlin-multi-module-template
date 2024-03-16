package com.example.demo.filter

import com.example.demo.security.auth.jwt.JwtService
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.UUID
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import kotlin.Throws

@Component
class MDCFilter(
    private val jwtService: JwtService,
) : Filter {
    companion object {
        const val TRACE_ID = "trace_id"
        const val USER_ID = "user_id"
        const val REQUEST_URI = "request_uri"
        const val HTTP_METHOD = "http_method"
        const val TX_ID = "tx_id"
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain,
    ) {
        if (request is HttpServletRequest) {
            UUID.randomUUID().toString().also {
                MDC.put(TRACE_ID, it)
            }

            if (request.getHeader("Authorization") != null) {
                MDC.put(
                    USER_ID,
                    jwtService.getDecodedJwtFromAuthenticationHeader(request.getHeader("Authorization"))
                        ?.getClaim("userId")?.asLong().toString(),
                )
            }

            request.requestURI?.also { MDC.put(REQUEST_URI, it) }
            request.method?.also { MDC.put(HTTP_METHOD, it) }
            request.getHeader(TX_ID)?.also { MDC.put(TX_ID, it) }
            try {
                chain.doFilter(request, response)
            } finally {
                MDC.clear()
            }
        }
    }
}
