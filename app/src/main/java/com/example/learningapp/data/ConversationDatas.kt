package com.example.learningapp.data

import androidx.compose.ui.graphics.Color
import com.example.learningapp.R


data class Conversation(
    var color: Color,
    var title: String,
    var description: String,
    var cover: Int,
    var refId: String,
)

var dummy = listOf(Conversation(
    color = Color(190, 173, 250),
    description = "Mempelajari kosa kata tentang keluarga dan aktivitas sehari-hari.",
    title = "Keluarga",
    cover = R.drawable.gambar_2,
    refId = "aVzdekthMXdyKDsWW3Vp"
),Conversation(
    color = Color(255, 128, 128),
    title = "Sekolah",
    description = "Mempelajari kosa kata tentang sekolah dan aktivitas sehari-hari.",
    cover = R.drawable.gambar_3,
    refId = "aVzdekthMXdyKDsWW3Vp"
),Conversation(
    color = Color(218, 221, 177),
    title = "Pekerjaan",
    description = "Mempelajari kosa kata tentang pekerjaan dan aktivitas sehari-hari.",
    cover = R.drawable.family,
    refId = "aVzdekthMXdyKDsWW3Vp"
))

