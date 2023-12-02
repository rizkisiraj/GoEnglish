package com.example.learningapp.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import com.airbnb.lottie.utils.MiscUtils.lerp
import com.example.learningapp.R
import com.example.learningapp.SharedViewModel
import com.example.learningapp.data.Conversation
import com.example.learningapp.data.DetailsNavScreen
import com.example.learningapp.data.dummy
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(navController: NavHostController, uiViewModel: SharedViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CourseCarousel(navController, uiViewModel)
    }
}

var colors = listOf(
    listOf(Color(0xFF7752FE), Color(0xFF6044C6)),
    listOf(Color(0xFF83A2FF),Color(0xFF728BD3)),
    listOf(Color(0xFF7FD7C1), Color(0xFF70BDAA)),
)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CourseCarousel(navController: NavHostController, uiViewModel: SharedViewModel) {
    val page = rememberPagerState(pageCount = 3)
    var backgroundColor by remember { mutableStateOf(colors[0][1]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisposableEffect(page.currentPage) {
            // Update the background color when currentPage changes
            when(page.currentPage) {
                0 -> backgroundColor = colors[0][0]
                1 -> backgroundColor = colors[1][0]
                2 -> backgroundColor = colors[2][0]
            }

            // Dispose when the effect is removed
            onDispose { }
        }
        HorizontalPager(
            state = page
        ) { currentPage ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (page.currentPage - currentPage) + page.currentPageOffset
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.1f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                SlideContent(page = page, backgroundColor = colors[currentPage][0], cardColor = colors[currentPage][1], data = dummy[currentPage], navController, uiViewModel)
            }
        }
        HorizontalPagerIndicator(
            pagerState = page,
            activeColor = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlideContent(page: PagerState, backgroundColor: Color, cardColor: Color, data: Conversation, navController: NavHostController, uiViewModel: SharedViewModel) {
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = data.cover),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = data.title,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 7.dp)
                )

                Text(
                    fontWeight = FontWeight.Light,
                    text = "1/2 Chapter",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(13.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                    )

                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                            .background(backgroundColor)
                            .animateContentSize()
                    )
                }

                Text(
                    text = data.description,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 22.dp, bottom = 60.dp)
                )

                Button(
                    shape = RoundedCornerShape(11.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor
                    ),
                    onClick = {
                        uiViewModel.OnConvoClick(page.currentPage)
                        navController.navigate(DetailsNavScreen.Information.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)

                ) {
                    Text(
                        text = "LANJUTKAN",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }

    }
}