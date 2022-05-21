package com.example.nocashmain.entity

import javax.persistence.*

@Entity
@Table(name="user_table")
class UserEntity {

    constructor(_name : String,
                _lat : Double,
                _lon : Double,
                _phone : Int,
                _role : String,
                _payId : String,
                _login : String,
                _password : String
    ): this() {
        this.name = _name
        this.lat = _lat
        this.lon = _lon
        this.phone = _phone
        this.role = _role
        this.payId = _payId
        this.login = _login
        this.password = _password
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "lat", nullable = false)
    var lat: Double? = null

    @Column(name = "lon", nullable = false)
    var lon: Double? = null

    @Column(name = "phone", nullable = false)
    var phone: Int? = null

    @Column(name = "role", nullable = false)
    var role: String? = null

    @Column(name = "pay_id", nullable = false)
    var payId: String? = null

    @Column(name = "login", nullable = false)
    var login: String? = null

    @Column(name = "password", nullable = false)
    var password: String? = null

}