package com.example.myqurancompose.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@Composable
//fun LoadingScreenWithText(modifier: Modifier = Modifier) {
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center,
//        modifier = modifier.fillMaxSize()
//    ) {
//        CircularProgressIndicator(
//            modifier = Modifier.size(size = 28.dp),
//            color = Color.Red
//        )
//        Spacer(modifier = Modifier.width(width = 8.dp))
//        Text(text = "Loading...")
//    }
//}

@Composable
fun HomeLoadingWithShimmer() {

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    Column {
        repeat(16) {

            HomeLoadingWithShimmerItem(brush)
        }
    }
}

@Composable
fun HomeLoadingWithShimmerItem(brush: Brush) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Spacer(
                    modifier = Modifier
                        .width(102.dp)
                        .height(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(brush)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Spacer(
                    modifier = Modifier
                        .width(102.dp)
                        .height(12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(brush)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Spacer(
                    modifier = Modifier
                        .width(82.dp)
                        .height(12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(brush)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}

@Composable
fun DetailLoadingWithShimmer() {

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    Column {
        repeat(16) {
            DetailLoadingWithShimmer(brush = brush)
        }
    }
}

@Composable
fun DetailLoadingWithShimmer(brush: Brush) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Spacer(
                    modifier = Modifier
                        .background(brush)
                        .width(162.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .height(16.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                Spacer(
                    modifier = Modifier
                        .background(brush)
                        .width(2.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .height(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.width(4.dp))
                Spacer(
                    modifier = Modifier
                        .background(brush)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .padding(horizontal = 16.dp)
                        .height(36.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun HomeLoadingWithShimmerPreview() {
    HomeLoadingWithShimmerItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailLoadingWithShimmerPreview() {
    DetailLoadingWithShimmer()

}