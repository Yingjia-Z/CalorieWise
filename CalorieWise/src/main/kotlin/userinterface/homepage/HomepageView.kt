package userinterface.homepage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import viewmodel.homepage.HomepageViewModel


enum class HomepageComponent {
    FirstNameEvent,
    LastNameEvent,
    UppercaseEvent,
    LowercaseEvent,
    ResetEvent
}

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
fun HomepageView(homepageViewModel: HomepageViewModel,
                 onAddFoodClick: () -> Unit, onAddDrinkClick: () -> Unit, onAddExerciseClick: () -> Unit,) {
    val viewModel by remember { mutableStateOf(homepageViewModel) }

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
                // add details here
                PieChart()
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Food", style = MaterialTheme.typography.h5)
                        Button(onClick = { onAddFoodClick() },
                            shape = CircleShape) {
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
                        Button(onClick = { onAddDrinkClick() },
                            shape = CircleShape) {
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
                        Button(onClick = { onAddExerciseClick() },
                            shape = CircleShape) {
                            Text("+")
                        }
                    }
                }
            }
        }
    }
}