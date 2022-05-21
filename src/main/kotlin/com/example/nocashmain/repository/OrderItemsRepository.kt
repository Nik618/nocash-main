package com.example.nocashmain.repository

import com.example.nocashmain.entity.CategoryEntity
import com.example.nocashmain.entity.OrderEntity
import com.example.nocashmain.entity.OrderItemsEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemsRepository : CrudRepository<OrderItemsEntity, Int> {

}