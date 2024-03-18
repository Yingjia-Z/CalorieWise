package userinterface.analysis

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
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
import viewmodel.analysis.AnalysisPageViewModel
import kotlin.math.roundToInt

@Composable
fun PieChart(eaten: Int, total: Int, modifier: Modifier = Modifier) {
    val sweepAngle by animateFloatAsState(
        targetValue = if (total > 0) (eaten.toFloat() / total) * 360f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )
    val strokeWidth = 10

    Canvas(modifier = modifier.size(200.dp)) {
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
fun AnalysisPageView(analysisPageViewModel: AnalysisPageViewModel) {
    // val viewModel by remember { mutableStateOf(analysisPageViewModel) }
    val selectedButton = remember { mutableStateOf("Today") }
    val fatTotal = analysisPageViewModel.fatIntake.value
    val fatEaten = analysisPageViewModel.fatEaten.value
    //also carbs and protein
    val sugarTotal = analysisPageViewModel.sugarIntake.value
    val sugarEaten = analysisPageViewModel.sugarEaten.value
    val proteinTotal = analysisPageViewModel.proteinIntake.value
    val proteinEaten = analysisPageViewModel.proteinEaten.value


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Nutrients Board
        Card(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Nutrients Board", style = MaterialTheme.typography.subtitle2)
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = { selectedButton.value = "Today" }) {
                        Text("Today")
                    }
                    Button(onClick = { selectedButton.value = "Week" }) {
                        Text("Week")
                    }
                    Button(onClick = { selectedButton.value = "Month" }) {
                        Text("Month")
                    }
                    Modifier.padding(bottom = 20.dp)
                }
                if (selectedButton.value == "Today") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(50.dp),

                        ) {
                        Box(
                            modifier = Modifier.weight(1f).padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            PieChart(fatEaten, fatTotal)
                            val percentage = if (fatTotal > 0) {
                                (fatEaten.toFloat() / fatTotal * 100).roundToInt()
                            } else {
                                '?'
                            }
                            Text(text = "$percentage% of recommended \n Fats consumed")
                        }
                        //another two boxes for two charts
                        Box(
                            modifier = Modifier.weight(1f).padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            PieChart(sugarEaten, sugarTotal)//change parameters
                            val percentage = if (sugarTotal > 0) {
                                (sugarEaten.toFloat() / sugarTotal * 100).roundToInt()
                            } else {
                                '?'
                            }
                            Text(text = "$percentage% of recommended \n Sugar consumed")
                        }
                        Box(
                            modifier = Modifier.weight(1f).padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            PieChart(proteinEaten, proteinTotal)//change
                            val percentage = if (proteinTotal > 0) {
                                (proteinEaten.toFloat() / proteinTotal * 100).roundToInt()
                            } else {
                                '?'
                            }
                            Text(text = "$percentage% of recommended \n Protein consumed")
                        }

                    }
                }
            }
        }
    }
}
