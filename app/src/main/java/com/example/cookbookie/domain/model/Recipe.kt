package com.example.cookbookie.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val title: String,
    val category: String,
    val ingredients: String,
    val instructions: String,
    val image: ByteArray? = null,
    val favorite: Boolean = false,
    val rating: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
