package com.diva.recipeapp.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.diva.recipeapp.R
import com.diva.recipeapp.databinding.ActivityDetailRecipeBinding
import com.diva.recipeapp.core.domain.model.Recipe
import com.diva.recipeapp.core.utils.Constants.EXTRA_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipeBinding

    private val detailRecipeViewModel :  DetailRecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val detailRecipe = getParcelableExtra(intent, EXTRA_DATA, Recipe::class.java)
        showDetailRecipe(detailRecipe)
    }

    private fun showDetailRecipe(detailRecipe: Recipe?) {
        detailRecipe?.let {
            supportActionBar?.title = detailRecipe.name
            binding.contentDetailRecipe.apply {
                tvIngredients.text = detailRecipe.ingredients
                tvInstructions.text = detailRecipe.instructions

                // Set Data Tambahan
                tvCalories.text = "${detailRecipe.caloriesPerServing} kcal"
                tvTime.text = "${detailRecipe.prepTimeMinutes + detailRecipe.cookTimeMinutes} min"
                tvServings.text = "${detailRecipe.servings} Servings"
                tvCuisine.text = detailRecipe.cuisine
                chipDifficulty.text = detailRecipe.difficulty
            }
            Glide.with(this@DetailRecipeActivity)
                .load(detailRecipe.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailRecipe.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailRecipeViewModel.setFavoriteRecipe(detailRecipe, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }


    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}