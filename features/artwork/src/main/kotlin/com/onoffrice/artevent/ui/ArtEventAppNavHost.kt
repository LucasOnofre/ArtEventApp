package com.onoffrice.artevent.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.onoffrice.artevent.ui.detail.ArtWorkDetailScreen
import com.onoffrice.artevent.ui.favorites.ArtEventFavoritesScreen
import com.onoffrice.artevent.ui.list.ArtEventListScreen
import com.onoffrice.artevent.utils.ScaleTransitionDirection
import com.onoffrice.artevent.utils.scaleIntoContainer
import com.onoffrice.artevent.utils.scaleOutOfContainer
import com.onoffrice.artevent.utils.slideInAnimation
import com.onoffrice.artevent.utils.slideOutAnimation
import com.onoffrice.core.resources.ArtEventAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ArtEventAppNavHost(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Routes.ArtworkList.rout,
            Modifier.padding(innerPadding)
        ) {
            composable(Routes.ArtworkList.rout,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
                }) {
                ArtEventListScreen(navController = navController)
            }
            composable(Routes.ArtworkDetail.rout,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
                }) {
                ArtWorkDetailScreen(navController = navController)
            }
            composable(Routes.Favorites.rout,
                enterTransition = {
                    slideInAnimation()
                },
                exitTransition = {
                    slideOutAnimation()
                }) {
                ArtEventFavoritesScreen(navController = navController)
            }
        }
    }
}

@Composable
private fun CustomBottomNavigation(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val items = listOf(Routes.ArtworkList, Routes.Favorites)
        items.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.background(
                    ArtEventAppTheme.colors.blue
                ),
                icon = { Icon(screen.icon, contentDescription = null) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.rout } == true,
                onClick = {
                    navController.navigate(screen.rout) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}