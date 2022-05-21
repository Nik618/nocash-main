package com.example.nocashmain.dto

import com.example.nocashmain.entity.TransactionEntity
import com.example.nocashmain.entity.UserEntity
import java.util.*

data class Order (
    var id: Int? = null,
    var date: Date? = null,
    var idTransaction: Int? = null,
    var status: String? = null,
    var comment: String? = null,
    var cancelComment: String? = null,
    var paymentId: String? = null
)