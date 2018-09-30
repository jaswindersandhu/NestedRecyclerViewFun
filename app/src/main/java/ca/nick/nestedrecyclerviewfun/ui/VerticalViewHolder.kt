package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import ca.nick.nestedrecyclerviewfun.utils.StartSnapHelper
import kotlinx.android.synthetic.main.item_horizontal.view.*

class VerticalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val horizontalAdapter = HorizontalAdapter()
    private val snapHelper = StartSnapHelper()

    init {
        snapHelper.attachToRecyclerView(itemView.horizontal_recyclerview)
        itemView.horizontal_recyclerview.adapter = horizontalAdapter
    }

    fun bindViewHolder(item: List<Int>, scrollPosition: Int) {
        horizontalAdapter.submitList(item)
        getHorizontalLayoutManager().scrollToPositionWithOffset(scrollPosition, 0)
    }

    fun findFirstVisibleItemPosition() =
        getHorizontalLayoutManager().findFirstVisibleItemPosition()

    fun getHorizontalLayoutManager() =
        getHorizontalRecyclerView().layoutManager as LinearLayoutManager

    fun getHorizontalRecyclerView(): RecyclerView =
        itemView.horizontal_recyclerview
}