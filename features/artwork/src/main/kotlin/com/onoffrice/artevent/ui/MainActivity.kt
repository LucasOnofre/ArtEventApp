package com.onoffrice.artevent.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.onoffrice.core.resources.ArtEventAppTheme
import com.onoffrice.core.resources.ArtWorkAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArtWorkAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = ArtEventAppTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    ArtEventAppNavHost(navController)
                }
            }
        }
    }
}
