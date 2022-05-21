package com.example.nocashmain.repository

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<CategoryEntity, Int> {

}