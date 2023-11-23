package com.example.learningapp.data

import androidx.compose.ui.graphics.vector.ImageVector

sealed class DetailsNavScreen(
    val route: String,
) {
    object Information : DetailsNavScreen(
        route = "INFORMATION"
    )
    object Quiz : DetailsNavScreen(
        route = "QUIZ"
    )
}
