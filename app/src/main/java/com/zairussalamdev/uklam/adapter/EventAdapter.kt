package com.zairussalamdev.uklam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.model.Item
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.matchParent

class EventAdapter(
    private val context: Context,
    private val items: List<Item>,
    private val listener: (Item) -> Unit
) : RecyclerView.Adapter<EventAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Item, listener: (Item) -> Unit) {
            itemView.itemName.text = item.name
            itemView.setOnClickListener { listener(item) }
            Glide.with(itemView.context).load(item.photo?.split(";")?.get(0)).override(300, 150).diskCacheStrategy(DiskCacheStrategy.ALL).into(itemView.itemPhoto)
        }
    }
}