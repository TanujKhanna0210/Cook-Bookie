package com.example.cookbookie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cookbookie.domain.model.Recipe

@Database(
    entities = [Recipe::class],
    version = 5
)
@TypeConverters(
    RecipeTypeConverter::class
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract val recipeDao: RecipeDao
}