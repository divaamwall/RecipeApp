package com.diva.recipeapp.favorite.di

import android.content.Context
import com.diva.recipeapp.di.FavoriteModuleDependencies
import com.diva.recipeapp.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}