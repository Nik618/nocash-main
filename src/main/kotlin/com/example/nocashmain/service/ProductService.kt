package com.example.nocashmain.service

import com.example.nocashmain.dto.Order
import com.example.nocashmain.dto.Orders
import com.example.nocashmain.dto.Product
import com.example.nocashmain.dto.Products
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.repository.OrderRepository
import com.example.nocashmain.repository.ProductRepository
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
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    private var gson = Gson()

    @GetMapping("/api/product")
    fun getProduct(@RequestBody nameProduct : String, idCategory : CategoryEntity): String? {
        val products = Products()
        products.list = mutableListOf()
        val productEntities : List<ProductEntity?> = if ((!nameProduct.equals(null) || !idCategory.equals(null))) {
            productRepository.findAllByNameOrCategory(nameProduct, idCategory)
        } else
            productRepository.findAll()
        productEntities.forEach() {
            products.list.add(Product().apply {
                id = it?.id
                name = it?.name
                category = it?.category
                description = it?.description
                count = it?.count
            })
        }
        return gson.toJson(products)
    }

}