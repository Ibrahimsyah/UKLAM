package com.zairussalamdev.uklam.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.model.ListItem
import com.zairussalamdev.uklam.presenter.MainPresenter
import com.zairussalamdev.uklam.presenter.MainView
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity(), MainView {
    override fun showSplash() {
    }

    override fun hideSplash() {
        finish()
    }

    override fun showData(items: ListItem) {
        startActivity<MainActivity>("items" to items)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        val presenter = MainPresenter(this)
        presenter.grabData()
    }
}
