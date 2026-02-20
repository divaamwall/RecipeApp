package com.diva.recipeapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diva.recipeapp.core.databinding.ItemListRecipeBinding
import com.diva.recipeapp.core.domain.model.Recipe

class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListRecipeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Recipe) {
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.name
            binding.tvItemSubtitle.text = data.mealType
            
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Recipe> =
            object : DiffUtil.ItemCallback<Recipe>() {
                override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                    return oldItem == newItem
                }
            }
    }

}