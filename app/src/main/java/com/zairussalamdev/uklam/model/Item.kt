package com.zairussalamdev.uklam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val itemId: String?,
    val name: String?,
    val shortName: String?,
    val description: String?,
    val category: String?,
    val photo: String?,
    val location: String?,
    val address: String?
) : Parcelable {
    constructor() : this("", "", "", "", "", "", "", "")
}

@Parcelize
data class ListItem(val items: List<Item>) : Parcelable