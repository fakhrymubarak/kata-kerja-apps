package com.fakhry.katakerjaapps.adapter.book.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.databinding.ItemBookTransactionBinding
import com.fakhry.katakerjaapps.helper.Base64

class ItemBookTransactionAdapter(private val dataSet: List<BorrowedBook>) :
    RecyclerView.Adapter<ItemBookTransactionAdapter.ViewHolder>() {
    var onItemClick: ((BorrowedBook) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemBookTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BorrowedBook) {
            binding.apply {
                ivCover.setImageBitmap(Base64.decode(data.bookData.cover))
                tvTitle.text = data.bookData.title
                tvAuthor.text = data.bookData.author
                tvPublisher.text = data.bookData.publisher
                tvYearReleased.text = data.bookData.releaseYear
                tvDeadline.text = data.returnDate
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book_transaction, viewGroup, false)
        val binding = ItemBookTransactionBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
