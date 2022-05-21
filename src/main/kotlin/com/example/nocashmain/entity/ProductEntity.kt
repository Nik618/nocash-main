package com.example.nocashmain.entity

import javax.persistence.*

@Entity
@Table(name="product_table")
class ProductEntity {

    constructor(_name : String,
                _description : String,
                _count : Int,
                _category : CategoryEntity
    ): this() {
        this.name = _name
        this.description = _description
        this.count = _count
        this.category = _category
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "count", nullable = false)
    var count: Int? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "category", referencedColumnName = "id")
    var category: CategoryEntity? = null

}