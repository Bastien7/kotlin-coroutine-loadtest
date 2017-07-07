package com.bastien.calculation

/**
 * Small function that makes high CPU usage
 * Created by bastien on 05/07/2017.
 */
object Calculations {

    private val difficulty = 3_000_000

    fun computeHardWork(a: Double, b: Double, c: Double): Double {
        (1..difficulty).map {
            Math.hypot(a + it, b + 2 * it) * Math.exp(3 * c)
        }.sumByDouble { it }

        return Math.hypot(a, b) * Math.exp(c)
    }
}