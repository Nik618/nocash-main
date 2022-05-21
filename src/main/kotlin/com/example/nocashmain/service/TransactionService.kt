package com.example.nocashmain.service

import com.example.nocashmain.dto.*
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.TransactionEntity
import com.example.nocashmain.repository.ProductRepository
import com.example.nocashmain.repository.TransactionRepository
import com.example.nocashmain.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TransactionService {

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    private var gson = Gson()

    @PostMapping("/api/create/transaction")
    fun createTransaction(@RequestBody request : String): TransactionEntity? {
        val transaction: Transaction = gson.fromJson(request, object : TypeToken<Transaction>() {}.type)
        val transactionEntity = TransactionEntity().apply {
            date = Date()
            idUserTo = userRepository.findById(transaction.idUserTo!!).get()
            idUserFrom = userRepository.findById(transaction.idUserFrom!!).get()
            value = transaction.value
        }

        val userEntity = userRepository.findById(transaction.idUserFrom!!).get()
        userEntity.balance = userEntity.balance?.minus(transaction.value!!)
        userRepository.save(userEntity)

        return transactionRepository.save(transactionEntity)
    }

    @GetMapping("/api/transaction")
    fun getTransaction(): String? {
        val transactions = Transactions()
        transactions.list = mutableListOf()
        val transactionEntities = transactionRepository.findAll()
        transactionEntities.forEach() {
            transactions.list.add(Transaction().apply {
                id = it?.id
                date = it?.date
                idUserTo = it?.idUserTo?.id
                idUserFrom = it?.idUserFrom?.id
                value = it?.value
            })
        }

        return gson.toJson(transactions)
    }
}