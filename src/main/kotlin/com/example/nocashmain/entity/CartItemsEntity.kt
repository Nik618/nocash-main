package com.example.nocashmain.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import kotlinx.html.P
import org.apache.tomcat.jni.Local
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="cart_items_table")
open class CartItemsEntity {

    constructor(_idOrder : OrderEntity, _idProduct : ProductEntity, _count : Int): this() {
        this.idOrder = _idOrder
        this.idProduct = _idProduct
        this.count = _count
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    open var id: Long? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    open var idOrder: OrderEntity? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    open var idProduct: ProductEntity? = null

    @Column(name = "count", nullable = false)
    open var count: Int? = null

}