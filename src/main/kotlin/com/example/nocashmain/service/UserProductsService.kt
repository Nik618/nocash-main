package com.example.nocashmain.service

import com.example.nocashmain.dto.*
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

@CrossOrigin(origins = ["/**", "*"])
@RestController
class UserProductsService {

    @Autowired
    lateinit var userProductsRepository: UserProductsRepository

    @Autowired
    lateinit var userRepository: UserRepository

    private var gson = Gson()

    @GetMapping("/api/userproducts")
    fun getUserProducts(userId : Int): String? {
        val userProductsList = UserProductsList()
        userProductsList.list = mutableListOf()
        val userProductsEntities = userProductsRepository.findAllByIdUser(userRepository.findById(userId).get())
        userProductsEntities.forEach() {
            userProductsList.list.add(UserProducts().apply {
                    id = it?.id
                    idUser = it?.idUser!!.id!!
                    idProduct = it.idProduct!!.id!!
                }
            )
        }
        return gson.toJson(userProductsList)
    }

}