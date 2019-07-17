package com.zairussalamdev.uklam.presenter

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.uklam.model.Item
import com.zairussalamdev.uklam.model.ListItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val mainView: MainView
) {
    fun grabData() {
        mainView.showSplash()
        doAsync {
            val items: MutableList<Item> = mutableListOf()
            lateinit var listItems: ListItem
            val ref = FirebaseDatabase.getInstance().getReference("Items")
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (h in p0.children) {
                        val user = h.getValue(Item::class.java)
                        items.add(user!!)
                    }
                    listItems = ListItem(items)
                    mainView.hideSplash()
                    mainView.showData(listItems)
                }

                override fun onCancelled(p0: DatabaseError) {}

            })
        }
    }
}

interface MainView {
    fun showSplash()
    fun hideSplash()
    fun showData(items: ListItem)
}