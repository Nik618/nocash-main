package com.example.nocashmain.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "UserProductsList", strict = false)
class UserProductsList {

    @field:ElementList(entry = "UserProducts", inline = true)
    lateinit var list: MutableList<UserProducts>

}