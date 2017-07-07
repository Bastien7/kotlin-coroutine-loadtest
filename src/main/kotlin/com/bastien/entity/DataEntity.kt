package com.bastien.entity

import org.springframework.data.annotation.Id

/**
 * Created by bastien on 05/07/2017.
 */
data class DataEntity(@Id var id: String? = null, val informations: String = "")
