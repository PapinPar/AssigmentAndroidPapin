package com.chi.assigmentandroidpapin.ui.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chi.assigmentandroidpapin.R
import kotlinx.android.synthetic.main.image_item.view.*

class ImagesAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var urls: List<String> = listOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.image_item, p0, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context)
            .load(urls[position])
            .into((holder as ViewHolder).image)
    }

    fun setData(users: List<String>) {
        urls = users
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return urls.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.ivImage
    }
}