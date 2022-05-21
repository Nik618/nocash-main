package com.example.nocashmain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name="transaction_table")
class TransactionEntity {

    constructor(_date : Date, _idUserTo : UserEntity, _idUserFrom: UserEntity, _value : Double): this() {
        this.date = _date
        this.idUserTo = _idUserTo
        this.idUserFrom = _idUserFrom
        this.value = _value
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

    @Column(name = "value", nullable = false)
    var value: Double? = null

}