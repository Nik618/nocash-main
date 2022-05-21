package com.example.nocashmain.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.apache.tomcat.jni.Local
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity
@Table(name="white_list_table")
open class WhiteListEntity {

    constructor(_idUser : UserEntity, _dateAcc : Date, _accessToken : String, _dateRef : Date, _refreshToken : String): this() {
        this.idUser = _idUser
        this.dateAcc = _dateAcc
        this.accessToken = _accessToken
        this.dateRef = _dateRef
        this.refreshToken = _refreshToken
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    open var id: Int? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    open var idUser: UserEntity? = null

    @Column(name = "date_acc", nullable = false)
    open var dateAcc: Date? = null

    @Column(name = "access_token", nullable = false)
    open var accessToken: String? = null

    @Column(name = "date_ref", nullable = false)
    open var dateRef: Date? = null

    @Column(name = "refresh_token", nullable = false)
    open var refreshToken: String? = null
}