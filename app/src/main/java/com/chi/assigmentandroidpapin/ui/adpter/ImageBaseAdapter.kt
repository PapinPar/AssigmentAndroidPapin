package com.chi.assigmentandroidpapin.ui.adpter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chi.assigmentandroidpapin.net.model.User
import kotlinx.android.synthetic.main.image_item_avatar.view.*


class ImageBaseAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val items: ArrayList<User> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return ViewHolderAvatar(
            LayoutInflater.from(context).inflate(
                com.chi.assigmentandroidpapin.R.layout.image_item_avatar,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holer: RecyclerView.ViewHolder, poisition: Int) {
        Glide.with(context)
            .load(items[poisition].image)
            .apply(RequestOptions.circleCropTransform())
            .into((holer as ViewHolderAvatar).avarat)
        holer.name.text = items[poisition].name
        holer.innerAdapter.setData(items[poisition].items!!)
    }

    fun setData(users: List<User>) {
        items.addAll(users)
        notifyDataSetChanged()
    }
}

class ViewHolderAvatar(view: View) : RecyclerView.ViewHolder(view) {
    val avarat = view.ivAvatar
    val name = view.tvUserName
    val innerImage = view.rvInnerImage
    private var linearLayoutManager: GridLayoutManager = GridLayoutManager(view.context, 2)
    val innerAdapter = ImagesAdapter(view.context)

    init {
        innerImage.setHasFixedSize(true)
        innerImage.layoutManager = linearLayoutManager
        innerImage.adapter = innerAdapter
    }
}