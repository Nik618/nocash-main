package com.example.nocashmain.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Categories", strict = false)
class Categories {

    @field:ElementList(entry = "Category", inline = true)
    lateinit var list: MutableList<Category>

}