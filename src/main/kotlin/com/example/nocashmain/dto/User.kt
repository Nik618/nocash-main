package com.example.nocashmain.dto

data class User (
    var id: Int? = null,
    var name: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var phone: String? = null,
    var role: String? = null,
    var login: String? = null,
    var password: String? = null,
    var balance: Double? = null,
)