package com.bastien

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CoroutinePocApplication

fun main(args: Array<String>) {
    SpringApplication.run(CoroutinePocApplication::class.java, *args)
}
