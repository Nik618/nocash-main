package com.example.nocashmain.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Transactions", strict = false)
class Transactions {

    @field:ElementList(entry = "Transaction", inline = true)
    lateinit var list: MutableList<Transaction>

}