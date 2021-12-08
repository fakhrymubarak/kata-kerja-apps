package com.fakhry.katakerjaapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.databinding.ItemBookWishlistBinding
import com.fakhry.katakerjaapps.helper.Base64

class ItemBookWishlistAdapter(private val dataSet: List<BorrowedBook>) :
    RecyclerView.Adapter<ItemBookWishlistAdapter.ViewHolder>() {
    var onItemClick: ((BorrowedBook) -> Unit)? = null


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBookWishlistBinding.bind(view)
        fun bind(data: BorrowedBook) {
            binding.apply {
                ivCover.setImageBitmap(Base64.decode(data.cover))
                tvTitle.text = data.title
                tvAuthor.text = data.author
                tvPublisher.text = data.publisher
                tvYearReleased.text = data.releaseYear
                tvStock.text = tvStock.context.getString(R.string.stock, data.stock)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book_wishlist, viewGroup, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
