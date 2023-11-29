package com.example.learningapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningapp.R
import com.example.learningapp.components.OutlineBox

@Composable
@Preview
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Analisa()
    }
}

@Composable
fun Analisa(){
    val text1 = listOf("7+", "A+", "8")
    val text2 = listOf("Day Streak", "Grade", "Completed")
    val colors = listOf<CardColor>(
        CardColor(Color(0xFFE2F2FF), Color(0xFF53ADF0)),
        CardColor(Color(0xFFE2FFE3), Color(0xFF7CF053)),
        CardColor(Color(0xFFFFFCE2), Color(0xFFF0EA53))
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(25.dp)
            .fillMaxSize()
            .padding(top = 65.dp)

    ) {

        //Ikon Kucink
        Image(
            painter = painterResource(id = R.drawable.kucing_profil),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .align(Alignment.CenterHorizontally),
        )

        //Tulisan Atas
        Text(
            text = "Statistik Saya",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 17.dp)
        )

        //Kotak 3
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 23.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(text1.size) { index ->
                OutlineBox(
                    modifier = Modifier.weight(1f),
                    color = colors[index].container,
                    shadowColor = colors[index].shadowColor
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text1[index],
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text2[index],
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }

        //Garis
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp) // Lebar garis vertikal
                .background(Color.Gray)
        )

        //Tulisan Bawah
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sagittis arcu tellus, sed condimentum nisi ultrices sit amet. Nunc suscipit maximus erat, quis interdum erat fermentum in. Nam finibus odio id interdum tempor. ",
            fontSize = 13.sp,
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

data class CardColor(
    val container: Color,
    val shadowColor: Color,
)