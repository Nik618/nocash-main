package com.example.nocashmain.dto

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.UserEntity
import java.util.*

data class Product (
    var id: Int? = null,
    var name: String? = null,
    var category: Category? = null,
    var description: String? = null,
    var count: Int? = null,
    var price: Int? = null
)