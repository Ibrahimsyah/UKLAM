package com.zairussalamdev.uklam.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.model.Item
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        val item = intent.getParcelableExtra<Item>("item")

        detailToolbar.title = item.shortName
        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val photos = item.photo?.split(";")
        photos?.let {
            detailPhoto.pageCount = it.size
        }
        detailPhoto.setImageListener { position, imageView ->
            Glide.with(applicationContext).load(photos?.get(position)).into(imageView)
        }

        item.name?.let{
            itemName.visibility = View.VISIBLE
            itemName.text = it
        }
        itemDescription.text = item.description
        item.address?.let{
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favourite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
