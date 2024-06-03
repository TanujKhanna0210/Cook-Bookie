package com.example.cookbookie

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Recipe::class],
    version = 1
)
@TypeConverters(
    RecipeTypeConverter::class
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract val dao: RecipeDao
}