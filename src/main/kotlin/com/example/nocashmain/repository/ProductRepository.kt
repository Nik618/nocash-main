package com.example.nocashmain.repository

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<ProductEntity, Int> {
    fun findAllByNameOrCategory(name: String, idCategory : CategoryEntity): List<ProductEntity?>
    fun findAllByNameAndCategory(name: String, idCategory : CategoryEntity): List<ProductEntity?>
    override fun findAll(): List<ProductEntity?>
}