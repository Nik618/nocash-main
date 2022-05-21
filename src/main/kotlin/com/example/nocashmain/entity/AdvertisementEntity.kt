package com.example.nocashmain.entity

import javax.persistence.*

@Entity
@Table(name="advertisement_table")
class AdvertisementEntity {

    constructor(_text : String): this() {
        this.text = _text
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "text", nullable = false)
    var text: String? = null
}