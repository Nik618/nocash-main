package com.example.nocashmain.entity

import javax.persistence.*

@Entity
@Table(name="category_table")
class CategoryEntity {

    constructor(_name : String): this() {
        this.name = _name
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name", nullable = false)
    var name: String? = null
}