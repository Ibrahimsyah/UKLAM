package com.zairussalamdev.uklam.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.db.database
import com.zairussalamdev.uklam.model.FavItem
import com.zairussalamdev.uklam.model.Item
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private var isFav = true
    private lateinit var item: Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        item = intent.getParcelableExtra("item")
        detailToolbar.title = item.shortName
        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        database.use {
            val res = select(FavItem.TABLE_FAV).whereArgs("${FavItem.ITEM_ID}=\"${item.itemId}\"")
            isFav = res.parseList(classParser<FavItem>()).isNotEmpty()
        }

        val photos = item.photo?.split(";")
        photos?.let {
            detailPhoto.pageCount = it.size
        }
        detailPhoto.setImageListener { position, imageView ->
            Glide.with(applicationContext).load(photos?.get(position)).into(imageView)
        }

        item.name?.let {
            itemName.visibility = View.VISIBLE
            itemName.text = it
        }
        itemDescription.text = item.description
        if (item.address?.length != 0) {
            iconPin.visibility = View.VISIBLE
            tvLokasi.visibility = View.VISIBLE
            tvLokasi.text = item.address
            btnMaps.visibility = View.VISIBLE
            btnMaps.onClick {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.location)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favourite_menu, menu)
        if (isFav) menu.getItem(0).setIcon(R.drawable.ic_favourite_red)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem?): Boolean {
        if (menuItem?.itemId == android.R.id.home) super.onBackPressed()
        if (menuItem?.itemId == R.id.menu_favourite) {
            if (!isFav) {
                database.use {
                    insert(
                        FavItem.TABLE_FAV,
                        FavItem.ITEM_ID to item.itemId,
                        FavItem.ITEM_NAME to item.name,
                        FavItem.ITEM_SHORTNAME to item.shortName,
                        FavItem.ITEM_LOCATION to item.location,
                        FavItem.ITEM_CATEGORY to item.category,
                        FavItem.ITEM_DESCRIPTION to item.description,
                        FavItem.ITEM_ADDRESS to item.address,
                        FavItem.ITEM_PHOTO to item.photo
                    )
                }
                menuItem.setIcon(R.drawable.ic_favourite_red)
                toast("Ditambahkan ke Favorit")
            } else {
                database.use {
                    delete(FavItem.TABLE_FAV, "${FavItem.ITEM_ID}=\"${item.itemId}\"")
                }
                menuItem.setIcon(R.drawable.ic_favorite_white)
                toast("Dihapus dari Favorit")
            }
            isFav = !isFav
        }
        return super.onOptionsItemSelected(menuItem)
    }
}
