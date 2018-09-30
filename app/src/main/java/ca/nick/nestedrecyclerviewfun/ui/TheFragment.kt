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

    private lateinit var outerAdapter: TheOuterAdapter

    companion object {
        val TAG: String = TheFragment::class.java.simpleName
        private const val KEY_INNER_SCROLL_POSITIONS = "inner_scroll_positions"
        fun create() = TheFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_the, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val innerScrollPositions: SparseIntArrayParcelable =
            savedInstanceState?.getParcelable(KEY_INNER_SCROLL_POSITIONS) ?: SparseIntArrayParcelable()
        outerAdapter = TheOuterAdapter(innerScrollPositions)
        outer_recyclerview.adapter = outerAdapter
        // Only necessary for use with CollapsingToolbarLayout
        outer_recyclerview.isNestedScrollingEnabled = false
        outerAdapter.submitList(TheRepository.data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_INNER_SCROLL_POSITIONS, getInnerScrollPositions())
    }

    private fun getInnerScrollPositions(): SparseIntArrayParcelable {
        val innerScrollPositions = outerAdapter.innerScrollPositions

        // inner scroll positions are tracked as the viewholders are detached from their window.
        // if the user changed the scroll position of visible items and those items weren't
        // ever detached, then their positions wouldn't be tracked in outerAdapter.innerScrollPositions.
        // the logic below reconciles that absence.
        val layoutManager = outer_recyclerview.layoutManager as LinearLayoutManager
        val firstVisible = layoutManager.findFirstVisibleItemPosition()
        val lastVisible = layoutManager.findLastVisibleItemPosition()

        for (i in firstVisible..lastVisible) {
            val outerViewHolder =
                outer_recyclerview.findViewHolderForAdapterPosition(i) as TheOuterViewHolder
            innerScrollPositions.put(i, outerViewHolder.findFirstVisibleItemPosition())
        }

        return innerScrollPositions
    }
}