package com.example.nocashmain.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Products", strict = false)
class Products {

    @field:ElementList(entry = "Product", inline = true)
    lateinit var list: MutableList<Product>

}