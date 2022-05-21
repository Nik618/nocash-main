package com.example.nocashmain.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "CartItemsList", strict = false)
class CartItemsList {

    @field:ElementList(entry = "CartItems", inline = true)
    lateinit var list: MutableList<CartItems>

}