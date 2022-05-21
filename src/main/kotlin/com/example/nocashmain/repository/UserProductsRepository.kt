package com.example.nocashmain.repository

import com.example.nocashmain.entity.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProductsRepository : CrudRepository<UserProductsEntity, Int> {
    fun findAllByIdUser(idUser: UserEntity): List<UserProductsEntity?>
}