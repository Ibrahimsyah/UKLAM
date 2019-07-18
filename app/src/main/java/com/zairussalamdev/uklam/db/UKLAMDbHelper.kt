package com.zairussalamdev.uklam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.zairussalamdev.uklam.model.Item
import org.jetbrains.anko.db.*

class UKLAMDbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "UKLAMDatabase.db", null, 1) {
    companion object {
        private var instance: UKLAMDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): UKLAMDbHelper {
            if (instance == null) {
                instance = UKLAMDbHelper(ctx.applicationContext)
            }
            return instance as UKLAMDbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) = db.createTable(
        Item.TABLE_FAV, true,
        Item.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
        Item.ITEM_NAME to TEXT,
        Item.ITEM_SHORTNAME to TEXT,
        Item.ITEM_DESCRIPTION to TEXT,
        Item.ITEM_CATEGORY to TEXT,
        Item.ITEM_PHOTO to TEXT,
        Item.ITEM_LOCATION to TEXT,
        Item.ITEM_ADDRESS to TEXT
    )

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = db.dropTable(Item.TABLE_FAV, true)

}

val Context.database: UKLAMDbHelper
    get() = UKLAMDbHelper.getInstance(applicationContext)