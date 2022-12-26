package com.example.booklibrary.tools.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booklibrary.databinding.ListVerticalBinding
import com.example.booklibrary.tools.data.DataBookItem

class VerticalViewAdapter(
    private val dataBooks: ArrayList<DataBookItem.Result?>,
    private val listener: OnAdapterListener
) :
    RecyclerView.Adapter<VerticalViewAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        ListVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val result = dataBooks[position]


        holder.binding.apply {
            with(dataBooks[position]) {
                Glide.with(imgRvDiscovery.context).load(this!!.thumbnailUrl).into(imgRvDiscovery)
                tvTitleDiscoveryBooks.text = title
            }
        }
        holder.itemView.setOnClickListener {
            if (result != null) {
                listener.onClick(result)
            }
        }
    }

    override fun getItemCount() = dataBooks.size

    class CardViewHolder(val binding: ListVerticalBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<DataBookItem.Result>){
        this.dataBooks.clear()
        this.dataBooks.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(result : DataBookItem.Result)
    }
}