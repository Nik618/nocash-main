package com.example.nocashmain.dto

import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.entity.UserEntity

data class CartItems (
    var id: Int? = null,
    var idUser : Int? = null,
    var idProduct : Int? = null,
    var count : Int? = null
)