package com.example.nocashmain.dto

import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.entity.UserEntity
import java.util.*

data class Transaction (
    var id: Int? = null,
    var date: Date? = null,
    var idUserTo: Int? = null,
    var idUserFrom: Int? = null,
    var idProduct: Int? = null,
    var value: Double? = null
)