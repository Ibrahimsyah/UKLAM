package com.zairussalamdev.uklam.model

data class FavItem(
    val id : Long?,
    val itemId : String?,
    val name: String?,
    val shortName : String?,
    val description: String?,
    val category: String?,
    val photo: String?,
    val location: String?,
    val address: String?
){

    companion object{
        const val TABLE_FAV : String = "TABLE_FAV"
        const val ID : String = "ID_"
        const val ITEM_ID : String = "ITEM_ID"
        const val ITEM_NAME: String = "ITEM_NAME"
        const val ITEM_SHORTNAME : String = "ITEM_SHORTNAME"
        const val ITEM_DESCRIPTION : String = "ITEM_DESCRIPTION"
        const val ITEM_CATEGORY : String = "ITEM_CATEGORY"
        const val ITEM_PHOTO : String = "ITEM_PHOTO"
        const val ITEM_LOCATION : String = "ITEM_LOCATION"
        const val ITEM_ADDRESS : String = "ITEM_ADDRESS"
    }
}