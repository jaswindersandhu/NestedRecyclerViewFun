package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.DiffUtil

object VerticalDiffCallback : DiffUtil.ItemCallback<List<Int>>() {

    override fun areItemsTheSame(p0: List<Int>, p1: List<Int>): Boolean {
        return p0 == p1
    }

    override fun areContentsTheSame(p0: List<Int>, p1: List<Int>): Boolean {
        return p0 == p1
    }
}