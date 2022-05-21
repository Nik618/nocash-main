package com.example.nocashmain.entity

import javax.persistence.*

@Entity
@Table(name="user_table")
class UserEntity {

    constructor(_name : String,
                _lat : Double,
                _lon : Double,
                _phone : String,
                _role : String,
                _login : String,
                _password : String,
                _balance : Double
    ): this() {
        this.name = _name
        this.lat = _lat
        this.lon = _lon
        this.phone = _phone
        this.role = _role
        this.login = _login
        this.password = _password
        this.balance = _balance
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "lat", nullable = false)
    var lat: Double? = null

    @Column(name = "lon", nullable = false)
    var lon: Double? = null

    @Column(name = "phone", nullable = false)
    var phone: String? = null

    @Column(name = "role", nullable = false)
    var role: String? = null

    @Column(name = "login", nullable = false)
    var login: String? = null

    @Column(name = "password", nullable = false)
    var password: String? = null

    @Column(name = "balance", nullable = false)
    var balance: Double? = null

}