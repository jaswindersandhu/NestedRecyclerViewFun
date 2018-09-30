package ca.nick.nestedrecyclerviewfun.ui

import android.content.res.Configuration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.nick.nestedrecyclerviewfun.R
import ca.nick.nestedrecyclerviewfun.utils.SparseIntArrayParcelable

class VerticalAdapter(val horizonalScrollPositions: SparseIntArrayParcelable)
    : ListAdapter<List<Int>, VerticalViewHolder>(VerticalDiffCallback) {

    private val horizontalViewPool = RecyclerView.RecycledViewPool()

    companion object {
        private const val PORTRAIT_PREFETCH = 8
        private const val LANDSCAPE_PREFETCH = 14
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal, parent, false)
            .run { VerticalViewHolder(this) }
            .apply {
                val isPortrait = parent.context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
                getHorizontalLayoutManager().initialPrefetchItemCount = if (isPortrait) {
                    PORTRAIT_PREFETCH
                } else {
                    LANDSCAPE_PREFETCH
                }
                getHorizontalRecyclerView().setRecycledViewPool(horizontalViewPool)
            }

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