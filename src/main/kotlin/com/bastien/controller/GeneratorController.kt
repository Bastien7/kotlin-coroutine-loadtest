package com.bastien.controller

import com.bastien.entity.DataEntity
import com.bastien.repository.DataRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by bastien on 05/07/2017.
 */
@RestController
class GeneratorController(val repository: DataRepository) {

    @GetMapping("/api/generator")
    fun resetData() {
        repository.deleteAll()
        (1..10_000).forEach {
            val dataEntity = DataEntity(informations = "There is plenty of informations! ($it)")
            repository.save(dataEntity)
        }
    }
}