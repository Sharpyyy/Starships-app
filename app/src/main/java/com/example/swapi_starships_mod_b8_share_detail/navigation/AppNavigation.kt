package com.example.swapi_starships_mod_b8_share_detail.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swapi_starships_mod_b8_share_detail.ui.detail.StarshipDetailScreen
import com.example.swapi_starships_mod_b8_share_detail.ui.list.StarshipsListScreen

const val LIST_ROUTE = "list"
const val DETAIL_ROUTE = "detail/{starshipId}"
const val STARSHIP_ID_ARG = "starshipId"

fun detailRoute(starshipId: String) = "detail/$starshipId"

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = LIST_ROUTE
    ) {
        composable(LIST_ROUTE) {
            StarshipsListScreen(
                onStarshipClick = { id ->
                    navController.navigate(detailRoute(id))
                }
            )
        }
        composable(
            route = DETAIL_ROUTE,
            arguments = listOf(navArgument(STARSHIP_ID_ARG) { type = NavType.StringType })
        ) {
            StarshipDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}
