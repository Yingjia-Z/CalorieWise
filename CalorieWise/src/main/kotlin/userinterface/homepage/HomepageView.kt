package userinterface.homepage

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import viewmodel.homepage.HomepageViewModel

@Composable
fun PieChart(eaten: Int, totalCal: Int, burned: Int, modifier: Modifier = Modifier) {
    val total = totalCal + burned
    val sweepAngle by animateFloatAsState(
        targetValue = if (total > 0) (eaten.toFloat() / total) * 360f else 0f, ////OpenAI. (2024). ChatGPT (March 28 version) [Large language model]. https://chat.openai.com/chat
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        )
    )
    val strokeWidth = 30

    Canvas(modifier = modifier.size(250.dp)) {
        drawArc(
            color = Color.Red,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth.toFloat())
        )

        drawArc(
            color = Color.LightGray,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeWidth.toFloat(), cap = StrokeCap.Round)
        )
    }
}

@Composable
fun tracker(eaten: Int, totalCal: Int, burned: Int) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$eaten kcal eaten",
                modifier = Modifier.padding(end = 16.dp)
            )
            PieChart(eaten = eaten, totalCal = totalCal, burned = burned)
            Text(
                text = "$burned kcal burned",
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = "${totalCal - eaten + burned} kcal remaining"
        )
    }
}


@Composable
fun HomepageView(
    homepageViewModel: HomepageViewModel,
    onAddFoodClick: () -> Unit, onAddDrinkClick: () -> Unit, onAddExerciseClick: () -> Unit,
) {
    val viewModel by remember { mutableStateOf(homepageViewModel) }
    val eaten = homepageViewModel.calorieEaten.value
    val totalCal = homepageViewModel.calorieIntake.value
    val burned = homepageViewModel.calorieBurned.value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // Calorie Tracker
        Card(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Calorie Tracker", style = MaterialTheme.typography.subtitle2)
                tracker(eaten, totalCal, burned)
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Food", style = MaterialTheme.typography.h5)
                        Button(
                            onClick = { onAddFoodClick() },
                            shape = CircleShape
                        ) {
                            Text("+")
                        }
                    }
                }
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Drinks", style = MaterialTheme.typography.h5)
                        Button(
                            onClick = { onAddDrinkClick() },
                            shape = CircleShape
                        ) {
                            Text("+")
                        }
                    }
                }
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Exercise", style = MaterialTheme.typography.h5)
                        Button(
                            onClick = { onAddExerciseClick() },
                            shape = CircleShape
                        ) {
                            Text("+")
                        }
                    }
                }
            }
        }
    }
}