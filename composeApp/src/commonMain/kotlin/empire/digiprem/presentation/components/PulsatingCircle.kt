package empire.digiprem.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement =Arrangement.Center
    ) {
        PulsatingCircle()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Chargement...", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        RealEstateSkeleton()
        Spacer(modifier = Modifier.height(16.dp))
        DotsLoadingAnimation()

    }
}

@Composable
fun PulsatingCircle() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = 0.8f
            }
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0x882196F3)),
                    center = Offset.Unspecified,
                    radius = 200f
                ),
                shape = CircleShape
            )
    )
}


@Composable
fun RealEstateSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Image Placeholder
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title Placeholder
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(20.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Text Placeholder
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun SkeletonBox(modifier: Modifier = Modifier) {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(
        modifier = modifier
            .background(brush)
    )
}





@Composable
fun DotsLoadingAnimation(
    dotSize: Dp = 12.dp,
    spaceBetween: Dp = 8.dp,
    travelDistance: Dp = 10.dp,
    color: Color = Color.Gray
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    val delays = listOf(0, 100, 200)

    Row(horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
        repeat(3) { index ->
            val anim = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = -travelDistance.value,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = delays[index], // ✅ Bon paramètre ici
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "dot$index"
            )

            Box(
                modifier = Modifier
                    .size(dotSize)
                    .offset(y = anim.value.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}





