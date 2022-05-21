package com.example.nocashmain.repository

import com.example.nocashmain.entity.TransactionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<TransactionEntity, Int> {

}