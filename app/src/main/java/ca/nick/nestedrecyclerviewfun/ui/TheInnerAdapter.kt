package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.nestedrecyclerviewfun.R

class TheInnerAdapter : ListAdapter<Int, TheInnerViewHolder>(TheInnerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inner, parent, false)
            .run { TheInnerViewHolder(this) }

    override fun onBindViewHolder(item: TheInnerViewHolder, position: Int) {
        item.bindViewHolder(getItem(position))
    }
}