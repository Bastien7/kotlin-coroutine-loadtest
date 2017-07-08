package com.bastien.utils

import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletResponse


/**
 * Created by bastien on 08/07/2017.
 */
@Component
class SimpleCORSFilter : Filter {

    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "GET")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
        response.setHeader("Access-Control-Expose-Headers", "Location")
        chain.doFilter(req, res)
    }

    override fun init(filterConfig: FilterConfig) {}

    override fun destroy() {}
}