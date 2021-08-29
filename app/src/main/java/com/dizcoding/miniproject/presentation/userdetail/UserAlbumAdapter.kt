package com.dizcoding.miniproject.presentation.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.miniproject.R
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Photo
import com.dizcoding.miniproject.databinding.ItemAlbumBinding
import com.dizcoding.miniproject.databinding.ItemAlbumTitleBinding
import com.dizcoding.miniproject.databinding.ItemPhotoBinding
import com.dizcoding.miniproject.external.adapter.setUp
import com.squareup.picasso.Picasso

class UserAlbumAdapter(
    private val items: List<AlbumItem>,
    private val callback: AlbumPhotoCallback
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.item_album_title -> {
                return UserAlbumTitle(
                    ItemAlbumTitleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            R.layout.item_album -> {
                return UserAlbumPhoto(
                    ItemAlbumBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), callback
                )
            }
            else -> {
                return UserAlbumTitle(
                    ItemAlbumTitleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_album_title -> {
                (holder as UserAlbumTitle).bind(items[position])
            }
            R.layout.item_album -> {
                (holder as UserAlbumPhoto).bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].itemViewType
    }
}

class UserAlbumTitle(private val binding: ItemAlbumTitleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AlbumItem) {
        binding.tvAlbumName.text = item.title
    }
}

class UserAlbumPhoto(
    private val binding: ItemAlbumBinding,
    private val callback: AlbumPhotoCallback
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AlbumItem) {
        binding.rvPhoto.setUp(
            item.photos,
            R.layout.item_photo,
            {
                val itemBinding = ItemPhotoBinding.bind(this)
                setImageView(it.thumbnailUrl.toString(), itemBinding.ivPhoto)
            },
            {
                callback.onPhotoClicked(it)
            },
            GridLayoutManager(binding.root.context, 4)
        )
    }

    private fun setImageView(imageSource: String, imgView: ImageView) {
        Picasso.get()
            .load(imageSource)
            .fit()
            .centerCrop()
            .into(imgView)
    }
}

interface AlbumPhotoCallback {
    fun onPhotoClicked(photo: Photo)
}