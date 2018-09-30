package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.nestedrecyclerviewfun.R
import ca.nick.nestedrecyclerviewfun.utils.SparseIntArrayParcelable
import ca.nick.nestedrecyclerviewfun.utils.isPortrait

class VerticalAdapter(val horizontalScrollPositions: SparseIntArrayParcelable)
    : ListAdapter<List<Int>, VerticalViewHolder>(VerticalDiffCallback) {

    private val horizontalViewPool = RecyclerView.RecycledViewPool()

    companion object {
        private const val PORTRAIT_PREFETCH = 8
        private const val LANDSCAPE_PREFETCH = 14
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vertical, parent, false)
            .run { VerticalViewHolder(this) }
            .apply {
                val isPortrait = parent.context.isPortrait()
                getHorizontalLayoutManager().initialPrefetchItemCount = if (isPortrait) {
                    PORTRAIT_PREFETCH
                } else {
                    LANDSCAPE_PREFETCH
                }
                getHorizontalRecyclerView().setRecycledViewPool(horizontalViewPool)
            }

    override fun onBindViewHolder(item: VerticalViewHolder, position: Int) {
        val scrollPosition = horizontalScrollPositions.get(position, 0)
        item.bindViewHolder(getItem(position), scrollPosition)
    }

    override fun onViewDetachedFromWindow(holder: VerticalViewHolder) {
        super.onViewDetachedFromWindow(holder)
        val itemPosition = holder.adapterPosition
        val scrollPosition = holder.findFirstVisibleItemPosition()
        horizontalScrollPositions.put(itemPosition, scrollPosition)
    }
}