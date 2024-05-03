package com.nafi.cryptospy.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.nafi.cryptospy.core.ViewHolderBinder
import com.nafi.cryptospy.data.model.Favorite
import com.nafi.cryptospy.databinding.ItemFavoriteBinding

interface FavoriteListener {
    fun onDeleteFavoriteClicked(favorite: Favorite)

    fun onItemClicked(coinId: String?)
}

class FavoriteListAdapter(private val favoriteListener: FavoriteListener? = null) :
    RecyclerView.Adapter<ViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<Favorite>() {
                override fun areItemsTheSame(
                    oldItem: Favorite,
                    newItem: Favorite,
                ): Boolean {
                    return oldItem.id == newItem.id && oldItem.coinId == newItem.coinId
                }

                override fun areContentsTheSame(
                    oldItem: Favorite,
                    newItem: Favorite,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(data: List<Favorite>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return if (favoriteListener != null) {
            FavoriteViewHolder(
                ItemFavoriteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                favoriteListener,
            )
        } else {
            FavoriteViewHolder(
                ItemFavoriteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                favoriteListener,
            )
        }
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        (holder as ViewHolderBinder<Favorite>).bind(dataDiffer.currentList[position])
    }
}

class FavoriteViewHolder(
    private val binding: ItemFavoriteBinding,
    private val favoriteListener: FavoriteListener?,
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Favorite> {
    override fun bind(data: Favorite) {
        setFavoriteData(data)
        setClickListener(data)
    }

    private fun setFavoriteData(data: Favorite) {
        with(binding) {
            ivCoinImage.load(data.coinImg) {
                crossfade(true)
            }
            tvCoinSymbol.text = data.coinSymbol
            tvCoinName.text = data.coinName
        }
    }

    private fun setClickListener(data: Favorite) {
        with(binding) {
            tvDelete.setOnClickListener { favoriteListener?.onDeleteFavoriteClicked(data) }
            itemView.setOnClickListener { favoriteListener?.onItemClicked(data.coinId) }
        }
    }
}
