package com.dizcoding.miniproject.external.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.miniproject.external.adapter.AbstractAdapter


class GenericAdapter<ITEM>(
    items: List<ITEM>,
    layoutResId: Int,
    private val bindHolder: View.(ITEM) -> Unit
) : AbstractAdapter<ITEM>(items, layoutResId) {

    private var itemClick: View.(ITEM) -> Unit = {}

    constructor(
        items: List<ITEM>,
        layoutResId: Int,
        bindHolder: View.(ITEM) -> Unit,
        itemClick: View.(ITEM) -> Unit = {}
    )
            : this(items, layoutResId, bindHolder) {
        this.itemClick = itemClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (position == holder.adapterPosition) {
            holder.itemView.bindHolder(itemList[position])
        }
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemView.itemClick(itemList[position])
    }
}

fun <ITEM> RecyclerView.setUp(
    items: List<ITEM>,
    layoutResId: Int,
    bindHolder: View.(ITEM) -> Unit,
    itemClick: View.(ITEM) -> Unit = {},
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
)
        : GenericAdapter<ITEM> {

    val genericAdapter by lazy {
        GenericAdapter(items, layoutResId, {
            bindHolder(it)
        }, {
            itemClick(it)
        })
    }

    layoutManager = manager
    adapter = genericAdapter
    adapter?.notifyDataSetChanged()

    return genericAdapter
}