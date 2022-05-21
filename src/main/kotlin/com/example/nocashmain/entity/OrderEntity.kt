package com.example.nocashmain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name="order_table")
class OrderEntity {

    constructor(_date : Date, _idTransaction : TransactionEntity, _status : String, _comment : String, _cancelComment : String, _paymentId : String): this() {
        this.date = _date
        this.idTransaction = _idTransaction
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
    @JoinColumn(name = "id_transaction", referencedColumnName = "id")
    var idTransaction: TransactionEntity? = null

    @Column(name = "status", nullable = false)
    var status: String? = null

    @Column(name = "comment")
    var comment: String? = null

    @Column(name = "cancel_comment")
    var cancelComment: String? = null

    @Column(name = "payment_id")
    var paymentId: String? = null

}