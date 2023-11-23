package com.example.learningapp.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

private val shadowSize = 4.dp

@Preview
@Composable
fun CobaSatu() {
    Box(
        Modifier.fillMaxSize()
    ) {
        BoxCard(
            modifier = Modifier.wrapContentWidth(),
            text = "Jahwa",
            type = "jawaban"
        ) {
        }
    }
}

@Composable
fun BoxCard(
    modifier: Modifier,
    text: String,
    type: String,
    color: Color = MaterialTheme.colorScheme.background,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowColor: Color = MaterialTheme.colorScheme.outline,
    onClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (back, btn) = createRefs()

        var animatedY by remember { mutableStateOf(0.dp) }

        Spacer(
            modifier = Modifier
                .constrainAs(back) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints

                    start.linkTo(btn.start)
                    end.linkTo(btn.end)
                    top.linkTo(btn.top)
                    bottom.linkTo(btn.bottom)

                    translationY = shadowSize
                }
                .clip(RoundedCornerShape(12.dp))
                .background(shadowColor)
        )

        val myAnim by animateDpAsState(
            targetValue = animatedY,
            animationSpec = tween(50),
            label = ""
        )

        var btnSize by remember { mutableStateOf(IntSize.Zero) }

        Box(
            modifier = Modifier
                .constrainAs(btn) {
                    width = Dimension.wrapContent
                    top.linkTo(parent.top)

                    translationY = myAnim
                }
                .border(
                    width = (1.2).dp,
                    color = shadowColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .indication(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                )
                .onGloballyPositioned {
                    btnSize = it.size
                }
                .clip(RoundedCornerShape(12.dp))
                .background(color)
                .padding(14.dp)
        ) {
            Text(text = text, fontSize = MaterialTheme.typography.bodyLarge.fontSize, textAlign = TextAlign.Center)
        }
    }
}