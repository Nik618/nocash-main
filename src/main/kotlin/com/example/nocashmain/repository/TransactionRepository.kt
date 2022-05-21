package com.example.nocashmain.repository

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.entity.TransactionEntity
import com.example.nocashmain.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<TransactionEntity, Int> {
    fun findAllByIdUserFromOrIdUserTo(idUserFrom: UserEntity, idUserTo: UserEntity): List<TransactionEntity?>
}