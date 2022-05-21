package com.example.nocashmain.service

import com.example.nocashmain.dto.Order
import com.example.nocashmain.dto.Orders
import com.example.nocashmain.entity.*
import com.example.nocashmain.repository.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.html.I
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.time.LocalDateTime
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


@RestController
class OrderService {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var orderItemsRepository: OrderItemsRepository

    @Autowired
    lateinit var cartItemsRepository: CartItemsRepository

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    private var gson = Gson()

    @PostMapping("/api/create/order")
    fun createOrder(@RequestBody request : String): OrderEntity? {
        val order: Order = gson.fromJson(request, object : TypeToken<Order>() {}.type)
        val orderEntity = OrderEntity().apply {
            date = Date()
            idTransaction = transactionRepository.findById(order.idTransaction!!).get()
            status = order.status
            comment = order.comment
            cancelComment = order.cancelComment
            paymentId = order.paymentId
        }

        //val cartItemsEntity = cartItemsRepository.findByIdUser(transactionRepository.findById(order.idTransaction!!).get().idUserFrom?.id!!).get(0)

        val cartItemsEntities = cartItemsRepository.findAllByIdUser(transactionRepository.findById(order.idTransaction!!).get().idUserFrom!!)

        var price = 0.0

        cartItemsEntities.forEach() {
            val orderItemsEntity = OrderItemsEntity().apply {
                idOrder = orderEntity
                idProduct = it?.idProduct
                count = it?.count
                price += count?.times(idProduct?.price!!)!!
                println(price)
            }

            orderItemsRepository.save(orderItemsEntity)
        }

        orderEntity.idTransaction?.value = price
        transactionRepository.save(orderEntity.idTransaction!!)
        return orderRepository.save(orderEntity)
    }

    @GetMapping("/api/order")
    fun getOrder(): String? {
        val orders = Orders()
        orders.list = mutableListOf()
        val orderEntities = orderRepository.findAll()
        orderEntities.forEach() {
            orders.list.add(Order().apply {
                id = it?.id
                date = it?.date
                idTransaction = it?.idTransaction?.id
                status = it?.status
                comment = it?.comment
                cancelComment = it?.cancelComment
                paymentId = it?.paymentId
            })
        }
        return gson.toJson(orders)
    }

    @PostMapping("/api/update/order")
    fun updateOrder(@RequestBody request : String): OrderEntity? {
        val order: Order = gson.fromJson(request, object : TypeToken<Order>() {}.type)
        val orderEntity = orderRepository.findById(order.id!!).get()

        orderEntity.date = Date()
        orderEntity.idTransaction = transactionRepository.findById(order.idTransaction!!).get()
        orderEntity.status = order.status
        orderEntity.comment = order.comment
        orderEntity.cancelComment = order.cancelComment
        orderEntity.paymentId = order.paymentId

        return orderRepository.save(orderEntity)
    }

    @GetMapping("/api/remove/order")
    fun removeOrder(id : Int): Unit? {
        val orderEntity = orderRepository.findById(id).get()
        return orderRepository.delete(orderEntity)
    }

}