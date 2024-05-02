package com.nafi.cryptospy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nafi.cryptospy.data.model.Coin
import com.nafi.cryptospy.databinding.ItemCryptoListBinding

class CoinAdapter(private val listener: (Coin) -> Unit) :
    RecyclerView.Adapter<CoinAdapter.ItemCoinViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<Coin>() {
                override fun areItemsTheSame(
                    oldItem: Coin,
                    newItem: Coin,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Coin,
                    newItem: Coin,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(data: List<Coin>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemCoinViewHolder {
        val binding = ItemCryptoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCoinViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: ItemCoinViewHolder,
        position: Int,
    ) {
        holder.bindView(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    class ItemCoinViewHolder(
        private val binding: ItemCryptoListBinding,
        val itemClick: (Coin) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Coin) {
            with(item) {
                binding.ivCryptoImages.load(item.imgUrl) {
                    crossfade(true)
                }
                binding.tvCryptoName.text = item.name
                binding.tvCryptoSymbol.text = item.symbol
                binding.tvCryptoPrice.text = "$${item.price}" // Only temporary
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}
