package com.example.nocashmain.service

import com.example.nocashmain.dto.Order
import com.example.nocashmain.dto.Orders
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.repository.OrderRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.html.I
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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

    private var gson = Gson()

    @PostMapping("/api/create/order")
    fun createOrder(@RequestBody request : String): OrderEntity? {
        val order: Order = gson.fromJson(request, object : TypeToken<Order>() {}.type)
        val orderEntity = OrderEntity().apply {
            date = Date()
            idUserTo = order.idUserTo
            idUserFrom = order.idUserFrom
            price = order.price
            status = order.status
            comment = order.comment
            cancelComment = order.cancelComment
            paymentId = order.paymentId
        }
        return orderRepository.save(orderEntity)
    }

    @GetMapping("/api/order")
    fun getOrder(@RequestBody name : String, idCategory : CategoryEntity): String? {
        val orders = Orders()
        orders.list = mutableListOf()
        val orderEntities = orderRepository.findAll()
        orderEntities.forEach() {
            orders.list.add(Order().apply {
                id = it?.id
                date = it?.date
                idUserTo = it?.idUserTo
                idUserFrom = it?.idUserFrom
                price = it?.price
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
        orderEntity.idUserTo = order.idUserTo
        orderEntity.idUserFrom = order.idUserFrom
        orderEntity.price = order.price
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