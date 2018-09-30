package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.nestedrecyclerviewfun.R
import ca.nick.nestedrecyclerviewfun.utils.SparseIntArrayParcelable
import kotlinx.android.synthetic.main.item_horizontal.view.*

class VerticalAdapter(val horizonalScrollPositions: SparseIntArrayParcelable)
    : ListAdapter<List<Int>, VerticalViewHolder>(VerticalDiffCallback) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal, parent, false)
            .also { it.horizontal_recyclerview.setRecycledViewPool(viewPool) }
            .run { VerticalViewHolder(this) }

    override fun onBindViewHolder(item: VerticalViewHolder, position: Int) {
        val scrollPosition = horizonalScrollPositions.get(position, 0)
        item.bindViewHolder(getItem(position), scrollPosition)
    }

    override fun onViewDetachedFromWindow(holder: VerticalViewHolder) {
        super.onViewDetachedFromWindow(holder)
        val itemPosition = holder.adapterPosition
        val scrollPosition = holder.findFirstVisibleItemPosition()
        horizonalScrollPositions.put(itemPosition, scrollPosition)
    }
}