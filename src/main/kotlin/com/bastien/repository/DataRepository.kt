package com.bastien.repository

import com.bastien.entity.DataEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Created by bastien on 05/07/2017.
 */
@Repository
interface DataRepository : MongoRepository<DataEntity, String>
