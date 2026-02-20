package com.diva.recipeapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.diva.recipeapp.R
import com.diva.recipeapp.core.data.Resource
import com.diva.recipeapp.databinding.FragmentHomeBinding
import com.diva.recipeapp.detail.DetailRecipeActivity
import com.diva.recipeapp.core.ui.RecipeAdapter
import com.diva.recipeapp.core.utils.Constants.EXTRA_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val recipeAdapter = RecipeAdapter()
            recipeAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailRecipeActivity::class.java)
                intent.putExtra(EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        homeViewModel.searchRecipe(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    homeViewModel.searchRecipe(newText ?: "")
                    return true
                }
            })

            homeViewModel.recipe.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    when (recipe) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.tvEmpty.visibility = View.GONE
                            binding.viewError.root.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.GONE

                            if (recipe.data.isNullOrEmpty()) {
                                binding.tvEmpty.visibility = View.VISIBLE
                                binding.rvRecipe.visibility = View.GONE
                            } else {
                                binding.tvEmpty.visibility = View.GONE
                                binding.rvRecipe.visibility = View.VISIBLE
                                recipeAdapter.submitList(recipe.data)
                            }
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvEmpty.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                recipe.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvRecipe) {
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