package com.diva.recipeapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.diva.recipeapp.core.data.Resource
import com.diva.recipeapp.core.domain.model.Recipe
import com.diva.recipeapp.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase
) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    private val _allRecipes = recipeUseCase.getAllRecipe().asLiveData()

    private val _recipe = MediatorLiveData<Resource<List<Recipe>>>()
    val recipe: LiveData<Resource<List<Recipe>>> = _recipe

    init {
        _searchQuery.value = ""

        _recipe.addSource(_allRecipes) { resource ->
            val query = _searchQuery.value ?: ""
            _recipe.value = filterRecipes(resource, query)
        }

        _recipe.addSource(_searchQuery) { query ->
            val resource = _allRecipes.value
            _recipe.value = filterRecipes(resource, query)
        }
    }

    private fun filterRecipes(resource: Resource<List<Recipe>>?, query: String): Resource<List<Recipe>> {
        return when (resource) {
            is Resource.Success -> {
                val filtered = if (query.isEmpty()) {
                    resource.data
                } else {
                    resource.data?.filter { recipe ->
                        recipe.name.contains(query, ignoreCase = true)
                    }
                }
                Resource.Success(filtered ?: emptyList())
            }
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(resource.message ?: "Error")
            null -> Resource.Loading()
        }
    }

    fun searchRecipe(query: String) {
        _searchQuery.value = query
    }
}