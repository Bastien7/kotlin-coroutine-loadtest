package com.bastien.utils

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * Created by bastien on 09/07/2017.
 */
//Extra easy ways to launch a coroutine
fun <T> go(job: suspend () -> T) = runBlocking { async(CommonPool) { job() } }

fun <T> goAndWait(job: suspend () -> T) = runBlocking { async(CommonPool) { job() }.await() }
