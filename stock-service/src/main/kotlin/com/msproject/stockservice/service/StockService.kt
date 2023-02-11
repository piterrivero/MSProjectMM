package com.msproject.stockservice.service

import com.msproject.stockservice.entity.Stock
import com.msproject.stockservice.repository.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StockService {

    @Autowired
    lateinit var stockRepository: StockRepository

    fun getAll(): MutableList<Stock> {
        return stockRepository.findAll();
    }

    fun addToStock(stock: Stock): Stock {
        return stockRepository.save(stock);
    }

    fun removeFromStock(stock: Stock): Stock {
        return stockRepository.save(stock);
    }

}