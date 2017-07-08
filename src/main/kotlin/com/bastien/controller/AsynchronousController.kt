package com.bastien.controller

import com.bastien.repository.DataRepository
import com.bastien.utils.Calculations
import com.bastien.utils.go
import com.bastien.utils.goAndWait
import info
import kotlinx.coroutines.experimental.delay
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

/**
 * Created by bastien on 05/07/2017.
 */
@RestController
@RequestMapping("/api/async")
class AsynchronousController(val repository: DataRepository) {
    private val REQUEST_WAITING = 100L // duration in seconds to simulate call to external services

    var totalElapsedTime: Long = 0L
    var requestCounter = 0
    var lastTimeReset: LocalDateTime = LocalDateTime.now()
    var lastTimeRequest: LocalDateTime = LocalDateTime.now()


    @GetMapping("/data")
    fun getManyData(): Long {
        go { delay(REQUEST_WAITING) }
        val elapsedTime = measureTimeMillis {
            goAndWait {
                Calculations.computeHardWork(32.0, 59.0 + 2, 48.0 + 3)
            }
        }
        totalElapsedTime += elapsedTime
        requestCounter++
        lastTimeRequest = LocalDateTime.now()
        //info("Calculation ($elapsedTime ms, $totalElapsedTime ms, average: ${totalElapsedTime / requestCounter})")
        return elapsedTime
    }

    @GetMapping("/data2")
    fun getManyData2() {
        go {
            delay(REQUEST_WAITING)
            val elapsedTime = measureTimeMillis {
                Calculations.computeHardWork(32.0, 59.0 + 2, 48.0 + 3)
            }
            totalElapsedTime += elapsedTime
            requestCounter++
            lastTimeRequest = LocalDateTime.now()
            //info("Calculation ($elapsedTime ms, $totalElapsedTime ms, average: ${totalElapsedTime / requestCounter})")

            elapsedTime
        }
    }

    @GetMapping("/reset")
    fun resetTime(): String {
        totalElapsedTime = 0
        requestCounter = 0
        lastTimeRequest = LocalDateTime.now()
        lastTimeReset = LocalDateTime.now()
        info("Async controller counters are reset")
        return "Async controller counters are reset"
    }

    @Scheduled(fixedRate = 1000)
    fun checkIfWorkIsDone() {
        val interval = Duration.between(lastTimeRequest, LocalDateTime.now()).seconds
        val interval2 = Duration.between(lastTimeReset, lastTimeRequest).seconds

        if (interval > 10 && interval2 != 0L) {
            val processDuration = Duration.between(lastTimeReset, lastTimeRequest).toMillis()
            info("The full process has taken $processDuration ms")
            resetTime()
        }
    }
}


