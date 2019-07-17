package com.zairussalamdev.uklam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zairussalamdev.uklam.Item
import com.zairussalamdev.uklam.R
import kotlinx.android.synthetic.main.item_recommend.view.*

class RecommendAdapter(
    private val context: Context,
    private val items: List<Item>,
    private val listener: (Item) -> Unit
) : RecyclerView.Adapter<RecommendAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recommend, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
        Glide.with(context).load(items[position].photo).into(holder.itemView.itemPhoto)
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Item, listener: (Item) -> Unit) {
            itemView.itemName.text = item.name
            itemView.setOnClickListener { listener(item) }
        }
    }
}