package com.example.nocashmain.service

import com.example.nocashmain.dto.*
import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.repository.CategoryRepository
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
class CategoryService {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    private var gson = Gson()

    @GetMapping("/api/category")
    fun getProduct(@RequestBody nameProduct : String, idCategory : CategoryEntity): String? {
        val categories = Categories()
        categories.list = mutableListOf()
        val categoryEntities = categoryRepository.findAll()
        categoryEntities.forEach() {
            categories.list.add(Category().apply {
                id = it?.id
                name = it?.name
            })
        }
        return gson.toJson(categories)
    }
}