package com.zairussalamdev.uklam.presenter

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.uklam.model.Item
import com.zairussalamdev.uklam.model.ListItem
import org.jetbrains.anko.doAsync

class MainPresenter(
    private val mainView: MainView
) {
    fun grabData() {
        mainView.showLoading()
        doAsync {
            val items: MutableList<Item> = mutableListOf()
            lateinit var listItems: ListItem
            val ref = FirebaseDatabase.getInstance().getReference("Items")
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (h in p0.children) {
                        val item = h.getValue(Item::class.java)
                        item?.let { items.add(it) }
                    }
                    listItems = ListItem(items)
                    mainView.hideLoading()
                    mainView.showData(listItems)
                }

                override fun onCancelled(p0: DatabaseError) {}

            })
        }
    }
    fun grabDataByCategory(category: String){
        mainView.showLoading()
        doAsync {
            val items: MutableList<Item> = mutableListOf()
            lateinit var listItems: ListItem
            val ref = FirebaseDatabase.getInstance().getReference("Items")
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (h in p0.children) {
                        val item = h.getValue(Item::class.java)
                        if(item?.category == category)items.add(item)
                    }
                    listItems = ListItem(items)
                    mainView.hideLoading()
                    mainView.showData(listItems)
                }

                override fun onCancelled(p0: DatabaseError) {}

            })
        }
    }
}

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showData(items: ListItem)
}