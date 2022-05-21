package com.example.nocashmain.service

import com.example.nocashmain.dto.*
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.TransactionEntity
import com.example.nocashmain.repository.TransactionRepository
import com.example.nocashmain.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class TransactionService {

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    private var gson = Gson()

    @PostMapping("/api/create/transaction")
    fun createOrder(@RequestBody request : String): TransactionEntity? {
        val transaction: Transaction = gson.fromJson(request, object : TypeToken<Order>() {}.type)
        val transactionEntity = TransactionEntity().apply {
            date = Date()
            idUserTo = transaction.idUserTo
            idUserFrom = transaction.idUserFrom
            idProduct = transaction.idProduct
            value = transaction.value
        }

        val userEntity = userRepository.findById(transaction.idUserFrom?.id!!).get()
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
                idUserTo = it?.idUserTo
                idUserFrom = it?.idUserFrom
                idProduct = it?.idProduct
                value = it?.value
            })
        }

        return gson.toJson(transactions)
    }
}