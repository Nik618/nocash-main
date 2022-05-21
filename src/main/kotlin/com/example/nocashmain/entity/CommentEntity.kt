package com.example.nocashmain.entity

import javax.persistence.*

@Entity
@Table(name="comment_table")
class CommentEntity {

    constructor(_name : String, _idUser : UserEntity): this() {
        this.text = _name
        this.idUser = _idUser
    }

    constructor()

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Int? = null

    @OneToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    var idUser: UserEntity? = null

    @Column(name = "text", nullable = false)
    var text: String? = null
}