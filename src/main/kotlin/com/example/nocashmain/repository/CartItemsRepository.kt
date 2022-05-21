package com.example.nocashmain.repository

import com.example.nocashmain.entity.CartItemsEntity
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemsRepository : CrudRepository<CartItemsEntity, Int> {
    fun findByIdUser(idUser: UserEntity): List<CartItemsEntity?>
    fun findAllByIdUser(idUser: UserEntity): List<CartItemsEntity?>
}