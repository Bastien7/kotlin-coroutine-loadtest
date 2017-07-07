package com.bastien.controller

import com.bastien.calculation.Calculations
import com.bastien.repository.DataRepository
import info
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.system.measureTimeMillis

/**
 * Created by bastien on 05/07/2017.
 */
@RestController
@RequestMapping("/api/async")
class AsynchronousController(val repository: DataRepository) {
    companion object {
        var totalElapsedTime: Long = 0L
        var requestCounter = 0
    }

    @GetMapping("/data")
    fun getManyData(): Long {
        go { delay(5000) }
        val elapsedTime = measureTimeMillis {
            go {
                Calculations.computeHardWork(32.0, 59.0 + 2, 48.0 + 3)
            }
        }
        totalElapsedTime += elapsedTime
        requestCounter++
        info("Calculation ($elapsedTime ms, $totalElapsedTime ms, average: ${totalElapsedTime / requestCounter})")

        return elapsedTime
    }

    @GetMapping("/reset")
    fun resetTime(): Int {
        totalElapsedTime = 0
        requestCounter = 0
        return 1
    }
}

//Extra easy way to launch a coroutine
fun <T> go(job: suspend () -> T) = runBlocking { async(CommonPool) { job() }.await() }