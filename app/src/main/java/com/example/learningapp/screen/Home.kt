package com.example.learningapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.learningapp.SharedViewModel
import com.example.learningapp.data.Conversation
import com.example.learningapp.data.ConversationDetailState
import com.example.learningapp.data.dummy

@Composable
fun HomeScreen(navController: NavHostController, uiViewModel: SharedViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(top = 64.dp)
                .padding(top = 64.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Select Topic",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
                StoryView(navController = navController, uiViewModel = uiViewModel)
            }
        }
    }
}

@Composable
fun StoryView(navController: NavHostController, uiViewModel: SharedViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        ) {
        itemsIndexed(dummy) { index, item ->
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                         uiViewModel.OnConvoClick(index)
                         navController.navigate("details__graph")
                    },
                colors = CardDefaults.cardColors(
                    containerColor = item.color
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        item.emoji,
                        textAlign = TextAlign.Center,
                        fontSize = 64.sp,
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = item.title,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}