package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.DiffUtil

object VerticalDiffCallback : DiffUtil.ItemCallback<List<Int>>() {

    override fun areItemsTheSame(oldItem: List<Int>, newItem: List<Int>) = oldItem == newItem
    override fun areContentsTheSame(oldItem: List<Int>, newItem: List<Int>) = oldItem == newItem
}