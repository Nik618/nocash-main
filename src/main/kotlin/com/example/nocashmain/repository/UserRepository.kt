package com.example.nocashmain.repository

import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserEntity, Long> {

}