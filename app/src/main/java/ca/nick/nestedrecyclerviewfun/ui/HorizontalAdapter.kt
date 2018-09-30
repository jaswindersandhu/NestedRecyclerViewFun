package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.nestedrecyclerviewfun.R

class HorizontalAdapter : ListAdapter<Int, HorizontalViewHolder>(HorizontalDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
            .run { HorizontalViewHolder(this) }

    override fun onBindViewHolder(item: HorizontalViewHolder, position: Int) {
        item.bindViewHolder(getItem(position))
    }
}