package com.example.nocashmain.repository

import com.example.nocashmain.entity.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<OrderEntity, Int> {
    fun findByIdTransaction(idTransaction: TransactionEntity): List<OrderEntity?>
}