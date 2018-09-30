package ca.nick.nestedrecyclerviewfun.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ca.nick.nestedrecyclerviewfun.R
import ca.nick.nestedrecyclerviewfun.data.TheRepository
import ca.nick.nestedrecyclerviewfun.utils.SparseIntArrayParcelable
import kotlinx.android.synthetic.main.fragment_the.*

class TheFragment : Fragment() {

    private lateinit var verticalAdapter: VerticalAdapter

    companion object {
        val TAG: String = TheFragment::class.java.simpleName
        private const val KEY_HORIZONTAL_SCROLL_POSITIONS = "horizontal_scroll_positions"
        fun create() = TheFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_the, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horizontalScrollPositions: SparseIntArrayParcelable =
            savedInstanceState?.getParcelable(KEY_HORIZONTAL_SCROLL_POSITIONS) ?: SparseIntArrayParcelable()
        verticalAdapter = VerticalAdapter(horizontalScrollPositions)
        vertical_recyclerview.adapter = verticalAdapter
        // Only necessary for use with CollapsingToolbarLayout
        vertical_recyclerview.isNestedScrollingEnabled = false
        verticalAdapter.submitList(TheRepository.verticalData)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_HORIZONTAL_SCROLL_POSITIONS, getHorizontalScrollPositions())
    }

    private fun getHorizontalScrollPositions(): SparseIntArrayParcelable {
        val horizontalScrollPositions = verticalAdapter.horizontalScrollPositions

        // horizontal scroll positions are tracked as the viewholders are detached from their window.
        // if the user changed the scroll position of visible items and those items weren't
        // ever detached, then their positions wouldn't be tracked in verticalAdapter.horizontalScrollPositions.
        // the logic below reconciles that absence.
        val layoutManager = getVerticalLayoutManager()
        val firstVisible = layoutManager.findFirstVisibleItemPosition()
        val lastVisible = layoutManager.findLastVisibleItemPosition()

        for (i in firstVisible..lastVisible) {
            val verticalViewHolder =
                vertical_recyclerview.findViewHolderForAdapterPosition(i) as VerticalViewHolder
            horizontalScrollPositions.put(i, verticalViewHolder.findFirstVisibleItemPosition())
        }

        return horizontalScrollPositions
    }

    private fun getVerticalLayoutManager() =
        vertical_recyclerview.layoutManager as LinearLayoutManager
}