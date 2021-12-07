package com.fakhry.katakerjaapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.databinding.ItemBookHomeBinding
import com.fakhry.katakerjaapps.helper.Base64

class ItemBookHomeAdapter(private val dataSet: List<BorrowedBook>) :
    RecyclerView.Adapter<ItemBookHomeAdapter.ViewHolder>() {
    var onItemClick: ((BorrowedBook) -> Unit)? = null


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBookHomeBinding.bind(view)
        fun bind(data: BorrowedBook) {
            binding.apply {
                ivCover.setImageBitmap(Base64.decode(data.cover))
                tvTitle.text = data.title
                tvAuthor.text = data.author
                tvPublisher.text = data.publisher
                tvYearReleased.text = data.releaseYear
                tvDeadline.text = data.returnDate
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book_home, viewGroup, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
