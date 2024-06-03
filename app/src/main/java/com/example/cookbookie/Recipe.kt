package com.example.cookbookie

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val title: String,
    val category: String,
    val ingredients: String,
    val instructions: String,
    val image: Bitmap? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
