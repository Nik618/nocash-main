package com.example.nocashmain.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import kotlinx.html.P
import org.apache.tomcat.jni.Local
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity
@Table(name="order_items_table")
open class UserProductsEntity {

    constructor(_idUser : UserEntity, _idProduct : ProductEntity): this() {
        this.idUser = _idUser
        this.idProduct = _idProduct
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    open var id: Long? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    open var idUser: UserEntity? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    open var idProduct: ProductEntity? = null

}