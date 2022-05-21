package com.example.nocashmain.dto

import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.entity.UserEntity

data class CartItems (
    var id: Int? = null,
    var idUser : UserEntity? = null,
    var idProduct : ProductEntity? = null,
    var count : Int? = null
)