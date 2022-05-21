package com.example.nocashmain.service

import com.example.nocashmain.dto.*
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

@CrossOrigin(origins = ["/**", "*"])
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
        val cartItems: CartItemsIn = gson.fromJson(request, object : TypeToken<CartItemsIn>() {}.type)
        val cartItemsEntity = CartItemsEntity().apply {
            idUser = userRepository.findById(cartItems.idUser!!).get()
            idProduct = productRepository.findById(cartItems.idProduct!!).get()
            count = cartItems.count
        }

        return cartItemsRepository.save(cartItemsEntity)
    }

    @GetMapping("/api/cartitems")
    fun getOrder(userId : Int): String? {
        val cartItemsList = CartItemsList()
        cartItemsList.list = mutableListOf()
        val cartItemsEntities = cartItemsRepository.findAllByIdUser(userRepository.findById(userId).get())
        cartItemsEntities.forEach() {
            cartItemsList.list.add(CartItems().apply {
                id = it?.id
                idUser = it?.idUser?.id
                idProduct = Product().apply {
                    id = it?.idProduct?.id
                    name = it?.idProduct?.name
                    category = Category().apply {
                        id = it?.idProduct?.category?.id
                        name = it?.idProduct?.category?.name
                    }
                    description = it?.idProduct?.description
                    count = it?.idProduct?.count
                    price = it?.idProduct?.price
                }
                count = it?.count
            })
        }
        return gson.toJson(cartItemsList)
    }

    @PostMapping("/api/update/cartitems")
    fun updateOrder(@RequestBody request : String): CartItemsEntity? {
        val cartItems: CartItemsIn = gson.fromJson(request, object : TypeToken<CartItemsIn>() {}.type)
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