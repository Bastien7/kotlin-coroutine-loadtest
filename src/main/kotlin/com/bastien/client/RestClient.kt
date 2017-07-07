package com.bastien.client

import info
import org.springframework.web.client.RestTemplate

/**
 * Created by bastien on 05/07/2017.
 */
object RestClient {
    private val BASE_URL = "http://localhost:8080/api"
    val restTemplate = RestTemplate()

    object mainAsync {
        @JvmStatic
        fun main(args: Array<String>) {
            restTemplate.getForObject("$BASE_URL/async/reset", Int::class.java)

            (1..20).toList().parallelStream().forEach {
                val restTemplate = RestTemplate()
                callAsyncController(restTemplate)
            }
        }
    }

    object mainBlocking {
        @JvmStatic
        fun main(args: Array<String>) {
            restTemplate.getForObject("$BASE_URL/blocking/reset", Int::class.java)

            (1..100).toList().parallelStream().forEach {
                val restTemplate = RestTemplate()
                callBlockingController(restTemplate)
            }
        }
    }

    fun callAsyncController(restTemplate: RestTemplate) {
        info("Request result: ${restTemplate.getForObject("$BASE_URL/async/data", Long::class.java)}")
    }
    fun callBlockingController(restTemplate: RestTemplate) {
        info("Request result: ${restTemplate.getForObject("$BASE_URL/blocking/data", Long::class.java)}")
    }
}