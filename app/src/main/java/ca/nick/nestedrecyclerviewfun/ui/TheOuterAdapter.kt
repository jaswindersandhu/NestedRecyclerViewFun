package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.nestedrecyclerviewfun.R
import ca.nick.nestedrecyclerviewfun.utils.SparseIntArrayParcelable

class TheOuterAdapter(val innerScrollPositions: SparseIntArrayParcelable)
    : ListAdapter<List<Int>, TheOuterViewHolder>(TheOuterDiffCallback) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_outer, parent, false)
            .run { TheOuterViewHolder(this, viewPool) }

    override fun onBindViewHolder(item: TheOuterViewHolder, position: Int) {
        val scrollPosition = innerScrollPositions.get(position, 0)
        item.bindViewHolder(getItem(position), scrollPosition)
    }

    override fun onViewDetachedFromWindow(holder: TheOuterViewHolder) {
        super.onViewDetachedFromWindow(holder)
        val itemPosition = holder.adapterPosition
        val scrollPosition = holder.findFirstVisibleItemPosition()
        innerScrollPositions.put(itemPosition, scrollPosition)
    }
}