package com.example.learningapp.components

import android.graphics.drawable.Icon
import android.health.connect.datatypes.units.Volume
import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.learningapp.ui.theme.Blue
import com.example.learningapp.ui.theme.BlueButtonShadow
import com.example.learningapp.ui.theme.BlueShadow
import com.example.learningapp.ui.theme.BlueVolume
import com.example.learningapp.ui.theme.Green
import com.example.learningapp.ui.theme.GreenShadow

private val shadowSize = 4.dp

@Preview
@Composable
fun CobaDua() {
    Box(
        Modifier.fillMaxSize()
    ) {
        VolumeButton(
            modifier = Modifier.fillMaxWidth(0.6f),
            size = 48.dp,
            icon = Icons.Filled.VolumeDown
        ) {
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VolumeButton(
    modifier: Modifier,
    color: Color = BlueVolume,
    textColor: Color = Color.Black,
    shadowColor: Color = BlueButtonShadow,
    size: Dp,
    icon: ImageVector,
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
                .clip(CircleShape)
                .background(shadowColor)
        )

        val myAnim by animateDpAsState(
            targetValue = animatedY,
            animationSpec = tween(50),
            label = ""
        )

        var btnSize by remember { mutableStateOf(IntSize.Zero) }

        Button(
            onClick = onClick,
            modifier = Modifier
                .constrainAs(btn) {
                    width = Dimension.wrapContent
                    top.linkTo(parent.top)

                    translationY = myAnim
                }
                .border(
                    width = (1.2).dp,
                    color = shadowColor,
                    shape = CircleShape
                )
                .indication(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                )
                .onGloballyPositioned {
                    btnSize = it.size
                }
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            animatedY = shadowSize
                            onClick()
                        }

                        MotionEvent.ACTION_UP -> {
                            animatedY = 0.dp
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val isOutside = it.x.toInt() !in 0..btnSize.width
                                    || it.y.toInt() !in 0..btnSize.height

                            if (isOutside) animatedY = 0.dp
                        }
                    }
                    true
                },
            contentPadding = PaddingValues(24.dp, 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = textColor
            ),
            shape = CircleShape
        ) {
            Icon(imageVector = icon, contentDescription = "volume button", modifier = Modifier.size(size))
        }
    }
}