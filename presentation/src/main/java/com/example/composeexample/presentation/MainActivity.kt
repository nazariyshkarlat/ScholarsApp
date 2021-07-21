package com.example.composeexample.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeexample.presentation.extensions.EnterAnimation
import com.example.composeexample.presentation.extensions.getArgument
import com.example.composeexample.presentation.feature.article_details.ui.ArticleDetailsScreen
import com.example.composeexample.presentation.feature.article_details.view_model.ArticleDetailsViewModel
import com.example.composeexample.presentation.feature.articles_list.ui.ArticlesListScreen
import com.example.composeexample.presentation.feature.articles_list.view_model.ArticlesListViewModel
import com.example.composeexample.presentation.navigation.NavigationDirections
import com.example.composeexample.presentation.navigation.NavigationManager
import com.example.composeexample.presentation.theme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

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
                if (command.pathTemplate.isNotEmpty()) {
                    navController.navigate(command.toString())
                }
            }

            NavHost(
                navController,
                startDestination = NavigationDirections.ArticlesList.route
            ) {
                composable(NavigationDirections.ArticlesList.route) { backStackEntry ->
                    val viewModel = org.koin.androidx.compose.getViewModel<ArticlesListViewModel>()
                    EnterAnimation {
                        ArticlesListScreen(viewModel)
                    }
                }
                composable(
                    route = NavigationDirections.ArticleDetails.route,
                    arguments = NavigationDirections.ArticleDetails.navArguments
                ){
                    val viewModel = org.koin.androidx.compose.getViewModel<ArticleDetailsViewModel>{
                        parametersOf(it.getArgument(NavigationDirections.ArticleDetails.Arguments.ARTICLE_ID))
                    }
                    EnterAnimation {
                        ArticleDetailsScreen(viewModel)
                    }
                }
            }
        }
    }

}