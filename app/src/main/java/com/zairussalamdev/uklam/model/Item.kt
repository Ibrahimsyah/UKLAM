package com.zairussalamdev.uklam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val name: String?,
    val shortName : String?,
    val description: String?,
    val category: String?,
    val photo: String?,
    val location: String?
) : Parcelable {
    constructor() : this("", "", "", "", "", "")

    companion object{
        val TABLE_FAV : String = "TABLE_FAV"
        val ID : String = "ID_"
        val ITEM_NAME: String = "ITEM_NAME"
        val ITEM_SHORTNAME : String = "ITEM_SHORTNAME"
        val ITEM_DESCRIPTION : String = "ITEM_DESCRIPTION"
        val ITEM_CATEGORY : String = "ITEM_CATEGORY"
        val ITEM_PHOTO : String = "ITEM_PHOTO"
        val ITEM_LOCATION : String = "ITEM_LOCATION"
    }
}

@Parcelize
data class ListItem(val items: List<Item>) : Parcelable