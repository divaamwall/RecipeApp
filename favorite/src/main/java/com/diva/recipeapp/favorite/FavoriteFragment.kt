package com.diva.recipeapp.favorite

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.diva.recipeapp.core.ui.RecipeAdapter
import com.diva.recipeapp.core.utils.Constants.EXTRA_DATA
import com.diva.recipeapp.di.FavoriteModuleDependencies
import com.diva.recipeapp.favorite.databinding.FragmentFavoriteBinding
import com.diva.recipeapp.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels{ factory }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val recipeAdapter = RecipeAdapter()

            recipeAdapter.onItemClick = { selectedData ->
                val intent = Intent().apply {
                    component = ComponentName(
                        "com.diva.recipeapp",
                        "com.diva.recipeapp.detail.DetailRecipeActivity"
                    )
                    putExtra(EXTRA_DATA, selectedData)
                }
                startActivity(intent)
            }

            favoriteViewModel.favoriteRecipe.observe(viewLifecycleOwner) { favoriteRecipes ->
                recipeAdapter.submitList(favoriteRecipes)
                binding.viewEmpty.root.visibility =
                    if (favoriteRecipes.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = recipeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}