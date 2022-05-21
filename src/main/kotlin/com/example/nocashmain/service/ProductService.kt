package com.example.nocashmain.service

import com.example.nocashmain.dto.Order
import com.example.nocashmain.dto.Orders
import com.example.nocashmain.dto.Product
import com.example.nocashmain.dto.Products
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.ProductEntity
import com.example.nocashmain.repository.CategoryRepository
import com.example.nocashmain.repository.OrderRepository
import com.example.nocashmain.repository.ProductRepository
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

    private var gson = Gson()

    @PostMapping("/api/create/product")
    fun createProduct(@RequestBody request : String): ProductEntity? {
        val product: Product = gson.fromJson(request, object : TypeToken<Product>() {}.type)

        val productEntity = ProductEntity().apply {
            name = product.name
            category = categoryRepository.findById(product.category!!).get()
            description = product.description
            count = product.count
        }

        return productRepository.save(productEntity)
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
                category = it?.category?.id
                description = it?.description
                count = it?.count
            })
        }
        return gson.toJson(products)
    }

    @PostMapping("/api/update/product")
    fun updateProduct(request : String): ProductEntity? {
        val product: Product = gson.fromJson(request, object : TypeToken<Product>() {}.type)
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