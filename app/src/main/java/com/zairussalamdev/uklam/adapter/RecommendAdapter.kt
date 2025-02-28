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
        val photoURL = items[position].photo?.split(";")?.get(0)
        Glide.with(context).load(photoURL).override(160,120).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.itemView.itemPhoto)
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Item, listener: (Item) -> Unit) {
            itemView.itemName.text = item.shortName
            itemView.setOnClickListener { listener(item) }
        }
    }
}