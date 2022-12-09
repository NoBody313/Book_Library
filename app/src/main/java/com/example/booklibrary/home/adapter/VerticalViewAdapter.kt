package com.example.booklibrary.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.booklibrary.databinding.ListVerticalBinding
import com.example.booklibrary.tools.data.DataBookItem

class VerticalViewAdapter(private val dataBooks: ArrayList<DataBookItem>) :
    RecyclerView.Adapter<VerticalViewAdapter.CardViewHolder>() {

    class CardViewHolder(val binding: ListVerticalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding =
            ListVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.binding.apply {
            with(dataBooks[position]) {
                Glide.with(imgRvDiscovery.context).load(thumbnailUrl).into(imgRvDiscovery)
                tvTitleDiscoveryBooks.text = title
            }
        }
    }

    override fun getItemCount() = dataBooks.size

//     {
//        fun bind(data: DataBookItem) {
//            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.thumbnailUrl)
//                    .apply(RequestOptions().override(350, 350))
//                    .into(imgRvDiscovery)
//
//                tvTitleDiscoveryBooks.text = data.title
//            }
//        }
//    }

}