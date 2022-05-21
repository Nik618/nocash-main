package com.example.nocashmain.dto

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.UserEntity
import java.util.*

data class ProductIn (
    var id: Int? = null,
    var name: String? = null,
    var category: Int? = null,
    var description: String? = null,
    var count: Int? = null,
    var price: Int? = null
)