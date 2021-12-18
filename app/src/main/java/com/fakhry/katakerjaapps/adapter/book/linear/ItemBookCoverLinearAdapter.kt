package com.fakhry.katakerjaapps.adapter.book.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.databinding.ItemBookCoverLinearBinding
import com.fakhry.katakerjaapps.helper.Base64

class ItemBookCoverLinearAdapter(private val dataSet: List<Book>) :
    RecyclerView.Adapter<ItemBookCoverLinearAdapter.ViewHolder>() {
    var onItemClick: ((Book) -> Unit)? = null


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBookCoverLinearBinding.bind(view)
        fun bind(data: Book) {
            binding.apply {
                ivCover.setImageBitmap(Base64.decode(data.cover))
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book_cover_linear, viewGroup, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
