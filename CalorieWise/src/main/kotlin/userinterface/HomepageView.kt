package userinterface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import controller.HomepageController
import androidx.compose.ui.unit.dp



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
fun HomepageView(homepageViewModel: HomepageViewModel, homepageController: HomepageController) {
    val viewModel by remember { mutableStateOf(homepageViewModel) }
    val controller by remember { mutableStateOf(homepageController) }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // Nutrients Board
                    Card(
                        modifier = Modifier.weight(1f),
                        elevation = 4.dp
                    ) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Nutrients Board", style = MaterialTheme.typography.h5)
                            // add details here
                            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                                Button(onClick = {
                                    controller.invoke(HomepageComponent.UppercaseEvent, null)
                                }) {
                                    Text("Today")
                                }
                                Button(onClick = {
                                    controller.invoke(HomepageComponent.LowercaseEvent, null)
                                }) {
                                    Text("Week")
                                }
                                Button(onClick = {
                                    controller.invoke(HomepageComponent.ResetEvent, null)
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

                    // Calorie Tracker
                    Card(

                        modifier = Modifier.weight(1f),
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text("Calorie Tracker", style = MaterialTheme.typography.h5)
                            // add details here
                            PieChart()
                            Card(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "Add Food", style = MaterialTheme.typography.h6)
                                    Button(onClick = {
                                        controller.invoke(HomepageComponent.LowercaseEvent, null)
                                    }, shape = CircleShape) {
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
                                    Text(text = "Add Drinks", style = MaterialTheme.typography.h6)
                                    Button(onClick = {
                                        controller.invoke(HomepageComponent.LowercaseEvent, null)
                                    }, shape = CircleShape) {
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
                                    Text(text = "Add Exercise", style = MaterialTheme.typography.h6)
                                    Button(onClick = {
                                        controller.invoke(HomepageComponent.LowercaseEvent, null)
                                    }, shape = CircleShape) {
                                        Text("+")
                                    }
                                }
                            }
                        }
                    }
                }

                // Intake and Workout of the day
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("Intake and Workout of the day", style = MaterialTheme.typography.h5)
                        // add details here
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Card(modifier = Modifier.fillMaxSize().weight(1f)) {
                                Text(text = "Food", style = MaterialTheme.typography.h6)
                                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                    HistoryEntry("Noodle", "+ 70", "20 g")
                                    HistoryEntry("Noodle", "+ 70", "20 g")
                                    HistoryEntry("Noodle", "+ 70", "20 g")
                                }
                            }
                            Card(modifier = Modifier.fillMaxSize().weight(1f)) {
                                Text(text = "Drink", style = MaterialTheme.typography.h6)
                                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                    HistoryEntry("Water", "+ 0", "200 ml")
                                    HistoryEntry("Water", "+ 0", "200 ml")
                                    HistoryEntry("Water", "+ 0", "200 ml")
                                }
                            }
                            Card(modifier = Modifier.fillMaxSize().weight(1f)) {
                                Text(text = "Exercise", style = MaterialTheme.typography.h6)
                                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                    HistoryEntry("Jogging", "- 70", "20 min")
                                    HistoryEntry("Jogging", "- 70", "20 min")
                                    HistoryEntry("Jogging", "- 70", "20 min")
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}