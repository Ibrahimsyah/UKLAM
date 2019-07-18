package com.zairussalamdev.uklam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.zairussalamdev.uklam.model.FavItem
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
        FavItem.TABLE_FAV, true,
        FavItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
        FavItem.ITEM_ID to TEXT + UNIQUE,
        FavItem.ITEM_NAME to TEXT,
        FavItem.ITEM_SHORTNAME to TEXT,
        FavItem.ITEM_DESCRIPTION to TEXT,
        FavItem.ITEM_CATEGORY to TEXT,
        FavItem.ITEM_PHOTO to TEXT,
        FavItem.ITEM_LOCATION to TEXT,
        FavItem.ITEM_ADDRESS to TEXT
    )

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = db.dropTable(FavItem.TABLE_FAV, true)

}

val Context.database: UKLAMDbHelper
    get() = UKLAMDbHelper.getInstance(applicationContext)