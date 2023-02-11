package com.msproject.stockservice.repository

import com.msproject.stockservice.entity.Stock
import org.springframework.data.mongodb.repository.MongoRepository

interface StockRepository : MongoRepository<Stock, Long> {

}