package com.msproject.stockservice.controller

import com.msproject.stockservice.entity.Stock
import com.msproject.stockservice.service.StockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stock")
class StockController {

    @Autowired
    lateinit var stockService: StockService;

    @GetMapping
    fun getAll() : MutableList<Stock>? {
        return stockService.getAll()
    }

    @PostMapping("/addToStock")
    fun addToStock(@RequestBody stock: Stock) : ResponseEntity<Stock> {
        var obj = stockService.addToStock(stock)
        return ResponseEntity<Stock>(stock, HttpStatus.OK)
    }

    @PostMapping("/removeFromStock")
    fun removeFromStock(@RequestBody stock: Stock) : ResponseEntity<Stock> {
        var obj = stockService.removeFromStock(stock)
        return ResponseEntity<Stock>(stock, HttpStatus.OK)
    }

}