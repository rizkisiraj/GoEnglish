package com.example.learningapp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.learningapp.data.DetailsNavScreen
import com.example.learningapp.screen.DetailConvoScreen
import com.example.learningapp.screen.HomeScreen
import com.example.learningapp.screen.LoadingScreen
import com.example.learningapp.screen.ProfileScreen
import com.example.learningapp.screen.SettingScreen
import com.example.learningapp.screen.SuccessScreen
import com.example.learningapp.screen.quiz.QuizListening
import com.example.learningapp.screen.quiz.QuizReading
import com.example.learningapp.screen.quiz.QuizSpeaking
import com.example.learningapp.utils.VoiceToTextParser
import com.example.learningapp.viewmodel.QuizViewModel
import com.example.learningapp.viewmodel.SharedViewModel

@Composable
fun BottomNavGraph(navController: NavHostController, voiceToTextParser: VoiceToTextParser) {
    var uiViewModel = SharedViewModel()

    NavHost(navController = navController, startDestination = BottomNavScreen.Home.route) {
        composable(route = BottomNavScreen.Home.route) {
            HomeScreen(navController = navController, uiViewModel = uiViewModel)
        }
        composable(route = BottomNavScreen.Analytic.route) {
            ProfileScreen()
        }
        composable(route = BottomNavScreen.Setting.route) {
            SettingScreen()
        }
        detailsNavGraph(navController, uiViewModel)
        quizScreen(navController = navController, quizViewModel = QuizViewModel(voiceToTextParser = voiceToTextParser))
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController, uiViewModel: SharedViewModel) {
    navigation(
        route = "details__graph",
        startDestination = DetailsNavScreen.Information.route
    ) {
        composable(
            route = DetailsNavScreen.Information.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                fadeIn(
                    tween(700)
                )
            }
            ) {
            DetailConvoScreen(uiViewModel = uiViewModel, navController = navController)
        }
    }
}


fun NavGraphBuilder.quizScreen(navController: NavHostController, quizViewModel: QuizViewModel) {
    navigation(
        route = "quiz__graph",
        startDestination = "Reading"
    ) {
        composable(route = "Listening",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                fadeIn(
                    tween(700)
                )
            }
            ) {
            QuizListening(quizViewModel = quizViewModel, navController = navController)
        }
        composable(route = "Reading",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                fadeIn(
                    tween(700)
                )
            }
            ) {
            QuizReading(quizViewModel = quizViewModel, navController = navController)
        }
        composable(route = "Speaking",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                fadeIn(
                    tween(700)
                )
            }
            ) {
            QuizSpeaking(quizViewModel = quizViewModel, navController = navController)
        }
        composable(route = "Finished",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                fadeIn(
                    tween(700)
                )
            }
            ) {
            SuccessScreen(navController = navController, quizViewModel)
        }
        composable(
            route = "Loading/{arg1}",
            arguments = listOf(
                navArgument("arg1") { type = NavType.StringType }
            )
        ) {navBackstackEntry ->
            val args = navBackstackEntry.arguments?.getString("arg1")

            args?.let {
                LoadingScreen(quizViewModel = quizViewModel, navController, refId = args)
            }
        }
    }
}

