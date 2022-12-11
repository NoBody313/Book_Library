package com.example.booklibrary.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booklibrary.databinding.ListVerticalBinding
import com.example.booklibrary.tools.data.DataBookItem
import com.example.booklibrary.ui.detail.BooksDetailActivity

class VerticalViewAdapter(private val dataBooks: ArrayList<DataBookItem.Result?>, val listener : OnAdapterListener) :
    RecyclerView.Adapter<VerticalViewAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        ListVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val result = dataBooks?.get(position)


        holder.binding.apply {
            with(dataBooks!![position]) {
                Glide.with(imgRvDiscovery.context).load(this!!.thumbnailUrl).into(imgRvDiscovery)
                tvTitleDiscoveryBooks.text = title
            }
        }
        holder.itemView.setOnClickListener {
            if (result != null) {
                listener.onClick(result)
            }
        }

//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, BooksDetailActivity::class.java)
//            intent.putExtra("" ,BooksDetailActivity::class.java)
//            it.context.startActivity(intent)
//        }
    }

    override fun getItemCount() = dataBooks!!.size

    class CardViewHolder(val binding: ListVerticalBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(data : List<DataBookItem.Result>){
        this.dataBooks!!.clear()
        this.dataBooks.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(result : DataBookItem.Result)
    }
}