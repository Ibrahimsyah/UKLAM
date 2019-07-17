package com.zairussalamdev.uklam

data class Item(
    val name: String?,
    val description: String?,
    val category: String?,
    val photo: String?,
    val location: String?
){
    constructor() : this("", "", "", "", "")
}