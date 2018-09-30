package ca.nick.nestedrecyclerviewfun.ui

import androidx.recyclerview.widget.DiffUtil

object HorizontalDiffCallback : DiffUtil.ItemCallback<Int>() {

    override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
}