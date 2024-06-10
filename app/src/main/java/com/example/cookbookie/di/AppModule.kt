package com.example.cookbookie.di

import android.app.Application
import androidx.room.Room
import com.example.cookbookie.data.local.RecipeDao
import com.example.cookbookie.data.local.RecipeDatabase
import com.example.cookbookie.data.repository.RecipeRepositoryImpl
import com.example.cookbookie.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRecipeDb(
        application: Application
    ) = Room.databaseBuilder(
        context = application,
        klass = RecipeDatabase::class.java,
        name = "recipe.db"
    )
//        .addTypeConverter(RecipeTypeConverter())
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideRecipeDao(
        recipeDb: RecipeDatabase
    ) = recipeDb.recipeDao

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeDao: RecipeDao
    ): RecipeRepository = RecipeRepositoryImpl(
        recipeDao = recipeDao
    )
}