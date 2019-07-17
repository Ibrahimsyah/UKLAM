package com.zairussalamdev.uklam

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.adapter.RecommendAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val items: MutableList<Item> = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (h in p0.children) {
                    val user = h.getValue(Item::class.java)
                    items.add(user!!)
                }
                rvRecommend.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
                rvRecommend.adapter = RecommendAdapter(applicationContext, items) {}
                rvEvent.layoutManager = LinearLayoutManager(applicationContext)
                rvEvent.adapter = EventAdapter(applicationContext, items) {}
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


    }

}
