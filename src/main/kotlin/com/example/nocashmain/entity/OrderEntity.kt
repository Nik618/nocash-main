package com.example.nocashmain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name="order_table")
class OrderEntity {

    constructor(_date : Date, _idUserTo : UserEntity, _idUserFrom: UserEntity, _price : Double, _status : String, _comment : String, _cancelComment : String, _paymentId : String): this() {
        this.date = _date
        this.idUserTo = _idUserTo
        this.idUserFrom = _idUserFrom
        this.price = _price
        this.status = _status
        this.comment = _comment
        this.cancelComment = _cancelComment
        this.paymentId = _paymentId
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "date", nullable = false)
    var date: Date? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_user_to", referencedColumnName = "id")
    var idUserTo: UserEntity? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_user_from", referencedColumnName = "id")
    var idUserFrom: UserEntity? = null

    @Column(name = "price", nullable = false)
    var price: Double? = null

    @Column(name = "status", nullable = false)
    var status: String? = null

    @Column(name = "comment")
    var comment: String? = null

    @Column(name = "cancel_comment")
    var cancelComment: String? = null

    @Column(name = "payment_id")
    var paymentId: String? = null

}