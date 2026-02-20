package com.diva.recipeapp.core.di

import android.content.Context
import androidx.room.Room
import com.diva.recipeapp.core.data.source.local.room.RecipeDao
import com.diva.recipeapp.core.data.source.local.room.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): RecipeDatabase {
        context.getDatabasePath("Recipe.db").delete()

        val passphrase: ByteArray = SQLiteDatabase.getBytes("recipe_secret_key".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            "Recipe.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideRecipeDao(database: RecipeDatabase): RecipeDao = database.recipeDao()

}