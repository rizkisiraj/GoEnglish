package com.example.learningapp.screen

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.learningapp.SharedViewModel
import com.example.learningapp.components.DuolingoButton
import com.example.learningapp.data.DetailsNavScreen
import com.example.learningapp.data.dummy
import com.example.learningapp.ui.theme.md_theme_light_background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailConvoScreen(uiViewModel: SharedViewModel, navController: NavHostController) {
    val dummyIndex = uiViewModel.getIndex()
    val convo = dummy[dummyIndex]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(convo.color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .scrollable(rememberScrollState(), Orientation.Vertical)
        ) {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = convo.color)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = convo.title,
                    fontSize = 64.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = convo.title,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .height(IntrinsicSize.Max)
            ) {
                Text(
                    "Select Chapter",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Divider()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                        .clickable {
                            navController.navigate("Loading")
                        }
                ) {
                    Text(
                        "Chapter #1",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                Divider()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                        .clickable {
                            navController.navigate("Loading")
                        }
                ) {
                    Text(
                        "Chapter #2",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                Divider()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                        .clickable {
                            navController.navigate("Loading")
                        }
                ) {
                    Text(
                        "Chapter #3",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                Divider()
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}