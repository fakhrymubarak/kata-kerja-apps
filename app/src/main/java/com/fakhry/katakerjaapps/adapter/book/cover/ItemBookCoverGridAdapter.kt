package com.fakhry.katakerjaapps.adapter.book.cover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.databinding.ItemBookCoverGridBinding
import com.fakhry.katakerjaapps.helper.Base64

class ItemBookCoverGridAdapter : RecyclerView.Adapter<ItemBookCoverGridAdapter.ViewHolder>() {

    val listData = ArrayList<Book>()
    var onItemClick: ((Book) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book_cover_grid, viewGroup, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    fun setData(newListData: List<Book>) {
        if (newListData.isEmpty()) {
            listData.clear()
        } else {
            listData.clear()
            listData.addAll(newListData)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBookCoverGridBinding.bind(view)
        fun bind(data: Book) {
            binding.apply {
                try {
                    ivCover.load(Base64.decode(data.cover))
                } catch (e: Exception) {
                    ivCover.load(R.drawable.ic_img_cover_default)
                }
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[layoutPosition])
            }
        }
    }
}
