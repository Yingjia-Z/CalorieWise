package userinterface.analysis

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import viewmodel.analysis.AnalysisPageViewModel

@Composable
fun PieChart(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(100.dp)
            .border(2.dp, Color.Transparent, RoundedCornerShape(10.dp))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Blue,
                radius = size.minDimension / 2,
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}

@Composable
fun HistoryEntry(name: String, calorie: String, quantity: String) {
    Row(
        modifier = Modifier.border(1.dp, Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = name, color = Color.Black)
            Text(text = "$calorie kcal", color = Color.Gray)
        }
        Text(
            text = quantity,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterVertically).padding(5.dp)
        )
    }
}


@Composable
fun AnalysisPageView(analysisPageViewModel: AnalysisPageViewModel) {
    val viewModel by remember { mutableStateOf(analysisPageViewModel) }

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
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Nutrients Board", style = MaterialTheme.typography.subtitle2)
                // add details here
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                    }) {
                        Text("Today")
                    }
                    Button(onClick = {
                    }) {
                        Text("Week")
                    }
                    Button(onClick = {
                    }) {
                        Text("Month")
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    PieChart()
                    PieChart()
                    PieChart()
                }
            }
        }
    }
}