package com.example.composeexample.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeexample.presentation.feature.articles_list.ArticlesListViewModel
import com.example.composeexample.presentation.feature.articles_list.ui.ArticlesListScreen
import com.example.composeexample.presentation.navigation.NavigationDirections
import com.example.composeexample.presentation.navigation.NavigationManager
import com.example.composeexample.presentation.theme.AppTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ProvideWindowInsets {
                    App()
                }
            }
        }
    }

    @Composable
    private fun App(){

        Box(modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
        ) {
            val navController = rememberNavController()

            NavigationManager.command.collectAsState().value.also { command ->
                if (command.destination.isNotEmpty()) {
                    navController.navigate(command.destination)
                }
            }

            NavHost(
                navController,
                startDestination = NavigationDirections.ArticlesList.destination
            ) {
                composable(NavigationDirections.ArticlesList.destination) { backStackEntry ->
                    val viewModel = getViewModel<ArticlesListViewModel>()
                    ArticlesListScreen(viewModel)
                }
            }
        }
    }

}