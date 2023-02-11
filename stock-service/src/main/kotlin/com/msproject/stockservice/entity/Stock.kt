package com.msproject.stockservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "stock")
data class Stock (

   @Id
   val id: Long,
   val discId: Long,
   val stock: Int

){}
