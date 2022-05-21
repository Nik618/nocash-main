package com.example.nocashmain.service

import com.example.nocashmain.dto.*
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.entity.UserProductsEntity
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

@CrossOrigin(origins = ["/**", "*"])
@RestController
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var userProductsRepository: UserProductsRepository

    @Autowired
    lateinit var userRepository: UserRepository

    private var gson = Gson()

    @PostMapping("/api/create/product")
    fun createProduct(@RequestBody request : String): ProductEntity? {
        val product: ProductIn = gson.fromJson(request, object : TypeToken<ProductIn>() {}.type)

        val productEntity = ProductEntity().apply {
            name = product.name
            category = categoryRepository.findById(product.category!!).get()
            description = product.description
            count = product.count
            price = product.price

        }

        productRepository.save(productEntity)

        userProductsRepository.save(UserProductsEntity().apply {
            idUser = userRepository.findById(product.userId!!).get()
            idProduct = productEntity
        })

        return productEntity
    }

    @GetMapping("/api/product")
    fun getProduct(nameProduct : String, idCategory : String): String? {
        val products = Products()
        products.list = mutableListOf()
        val productEntities : List<ProductEntity?> = if ((nameProduct == "" && idCategory == "")) {
            productRepository.findAll()
        } else if ((nameProduct == "" || idCategory == "") && !(nameProduct != "" && idCategory != "")) {
            productRepository.findAllByNameOrCategory(nameProduct, categoryRepository.findById(idCategory.toInt()).get())
        } else
            productRepository.findAllByNameAndCategory(nameProduct, categoryRepository.findById(idCategory.toInt()).get())

        productEntities.forEach() {
            products.list.add(Product().apply {
                id = it?.id
                name = it?.name
                category = Category().apply {
                    id = it?.category?.id
                    name = it?.category?.name
                }
                description = it?.description
                count = it?.count
                price = it?.price
            })
        }
        return gson.toJson(products)
    }

    @PostMapping("/api/update/product")
    fun updateProduct(request : String): ProductEntity? {
        val product: ProductIn = gson.fromJson(request, object : TypeToken<ProductIn>() {}.type)
        val productEntity = productRepository.findById(product.id!!).get()

        productEntity.name = product.name
        productEntity.category = categoryRepository.findById(product.category!!).get()
        productEntity.description = product.description
        productEntity.count = product.count

        return productRepository.save(productEntity)
    }

    @GetMapping("/api/remove/product")
    fun removeProduct(id : Int): Unit? {
        val productEntity = productRepository.findById(id).get()
        return productRepository.delete(productEntity)
    }

}