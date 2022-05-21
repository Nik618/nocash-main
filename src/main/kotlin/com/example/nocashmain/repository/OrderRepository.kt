package com.example.nocashmain.repository

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<OrderEntity, Int> {

}