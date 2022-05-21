package com.example.nocashmain.service

import com.example.nocashmain.dto.CartItems
import com.example.nocashmain.dto.CartItemsList
import com.example.nocashmain.dto.Order
import com.example.nocashmain.dto.Orders
import com.example.nocashmain.entity.*
import com.example.nocashmain.repository.CartItemsRepository
import com.example.nocashmain.repository.OrderRepository
import com.example.nocashmain.repository.ProductRepository
import com.example.nocashmain.repository.UserRepository
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

@CrossOrigin(origins = ["/**"], maxAge = 3600)
@RestController
class CartItemsService {

    @Autowired
    lateinit var cartItemsRepository: CartItemsRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    private var gson = Gson()

    @PostMapping("/api/create/cartitems")
    fun createOrder(@RequestBody request : String): CartItemsEntity? {
        val cartItems: CartItems = gson.fromJson(request, object : TypeToken<CartItems>() {}.type)
        val cartItemsEntity = CartItemsEntity().apply {
            idUser = userRepository.findById(cartItems.idUser!!).get()
            idProduct = productRepository.findById(cartItems.idProduct!!).get()
            count = cartItems.count
        }

        return cartItemsRepository.save(cartItemsEntity)
    }

    @GetMapping("/api/cartitems")
    fun getOrder(): String? {
        val cartItemsList = CartItemsList()
        cartItemsList.list = mutableListOf()
        val cartItemsEntities = cartItemsRepository.findAll()
        cartItemsEntities.forEach() {
            cartItemsList.list.add(CartItems().apply {
                id = it?.id
                idUser = it?.idUser?.id
                idProduct = it?.idProduct?.id
                count = it?.count
            })
        }
        return gson.toJson(cartItemsList)
    }

    @PostMapping("/api/update/cartitems")
    fun updateOrder(@RequestBody request : String): CartItemsEntity? {
        val cartItems: CartItems = gson.fromJson(request, object : TypeToken<Order>() {}.type)
        val cartItemsEntity = cartItemsRepository.findById(cartItems.id!!).get()

        cartItemsEntity.idUser = userRepository.findById(cartItems.idUser!!).get()
        cartItemsEntity.idProduct = productRepository.findById(cartItems.idProduct!!).get()
        cartItemsEntity.count = cartItems.count

        return cartItemsRepository.save(cartItemsEntity)
    }

    @GetMapping("/api/remove/cartItems")
    fun removeOrder(id : Int): Unit? {
        val cartItemsEntity = cartItemsRepository.findById(id).get()
        return cartItemsRepository.delete(cartItemsEntity)
    }

}