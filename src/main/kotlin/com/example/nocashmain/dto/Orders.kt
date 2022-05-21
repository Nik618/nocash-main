package com.example.nocashmain.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Orders", strict = false)
class Orders {

    @field:ElementList(entry = "Order", inline = true)
    lateinit var list: MutableList<Order>

}