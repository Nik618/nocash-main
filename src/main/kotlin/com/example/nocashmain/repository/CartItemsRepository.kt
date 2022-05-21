package com.example.nocashmain.repository

import com.example.nocashmain.entity.CartItemsEntity
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemsRepository : CrudRepository<CartItemsEntity, Int> {
    fun findByIdUser(id : Int): List<CartItemsEntity?>

}