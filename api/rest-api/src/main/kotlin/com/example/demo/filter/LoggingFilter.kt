package com.example.demo.filter

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import org.springframework.web.util.WebUtils
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.time.Instant
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingFilter() : OncePerRequestFilter() {
    companion object {
        private val log = KotlinLogging.logger {}
        private val EXCLUDE_HEADER_NAME: List<String> = mutableListOf("cookie", "postman-token", "connection", "accept-encoding", "accept")
        private val EXCLUDE_URL_PATTERN: List<String> =
            mutableListOf("/health", "/policy", "/terms", "/swagger", "/api-docs", "/actuator", "/internal", "/swagger-ui", "/.well-known")
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (EXCLUDE_URL_PATTERN.any { request.requestURI.contains(it) }) {
            filterChain.doFilter(request, response)
            return
        }

        val startTime = Instant.now().toEpochMilli()
        val requestToCache = ContentCachingRequestWrapper(request)
        val responseToCache = ContentCachingResponseWrapper(response)
        filterChain.doFilter(requestToCache, responseToCache)
        logRequest(request, requestToCache)
        logResponse(request, startTime, requestToCache, responseToCache)
    }

    private fun logRequest(
        request: HttpServletRequest,
        requestToCache: ContentCachingRequestWrapper,
    ) {
        log.info(
            "[REQUEST] : [ {} {}, header : {}, body : {} ]",
            request.method,
            request.servletPath,
            getHeaders(requestToCache),
            getRequestBody(requestToCache),
        )
    }

    @Throws(IOException::class)
    private fun logResponse(
        request: HttpServletRequest,
        startTime: Long,
        requestToCache: ContentCachingRequestWrapper,
        responseToCache: ContentCachingResponseWrapper,
    ) {
        log.info(
            "[RESPONSE] : [ {} {}, header : {}, body : {}, in : {} ]",
            request.method,
            request.servletPath,
            getHeaders(requestToCache),
            getResponseBody(responseToCache),
            (Instant.now().toEpochMilli() - startTime).toString() + "ms.",
        )
    }

    private fun getHeaders(request: HttpServletRequest): Map<String, Any> {
        val headerMap: MutableMap<String, Any> = HashMap()
        val headerArray = request.headerNames
        while (headerArray.hasMoreElements()) {
            val headerName = headerArray.nextElement()
            if (!EXCLUDE_HEADER_NAME.contains(headerName)) headerMap[headerName] = request.getHeader(headerName)
        }
        return headerMap
    }

    private fun getRequestBody(request: ContentCachingRequestWrapper): String? {
        val wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper::class.java)
        if (wrapper != null) {
            val buf = wrapper.contentAsByteArray
            if (buf.isNotEmpty()) {
                return try {
                    String(buf, charset(wrapper.characterEncoding))
                } catch (e: UnsupportedEncodingException) {
                    " - "
                }
            }
        }
        return " - "
    }

    @Throws(IOException::class)
    private fun getResponseBody(response: HttpServletResponse): String {
        var payload: String? = null
        val wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper::class.java)
        if (wrapper != null) {
            wrapper.characterEncoding = "UTF-8"
            val buf = wrapper.contentAsByteArray
            if (buf.isNotEmpty()) {
                payload = String(buf, charset(wrapper.characterEncoding))
                wrapper.copyBodyToResponse()
            }
        }
        return payload ?: " - "
    }
}
