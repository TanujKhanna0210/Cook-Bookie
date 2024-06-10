package com.example.cookbookie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cookbookie.presentation.navigation.NavGraph
import com.example.cookbookie.ui.theme.CookBookieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookieTheme {
                NavGraph()
            }
        }
    }
}