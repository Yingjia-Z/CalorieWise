package userinterface.records

import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import controller.UserController

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
fun RecordsView(recordsViewModel: RecordsViewModel, recordsController: UserController,
                onAddFoodClick: () -> Unit, onAddDrinkClick: () -> Unit, onAddExerciseClick: () -> Unit) {
    val viewModel by remember { mutableStateOf(recordsViewModel) }
    val controller by remember { mutableStateOf(recordsController) }

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
                Card(
                    modifier = Modifier.weight((1f))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Food", style = MaterialTheme.typography.h6)
                        Button(onClick = { onAddFoodClick() }, shape = CircleShape) {
                            Text("+")
                        }
                    }
                }
                Card(
                    modifier = Modifier.weight((1f))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Drinks", style = MaterialTheme.typography.h6)
                        Button(onClick = { onAddDrinkClick() }, shape = CircleShape) {
                            Text("+")
                        }
                    }
                }
                Card(
                    modifier = Modifier.weight((1f))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Add Exercise", style = MaterialTheme.typography.h6)
                        Button(onClick = { onAddExerciseClick() }, shape = CircleShape) {
                            Text("+")
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
                    Text("Intake and Workout of the day", style = MaterialTheme.typography.subtitle2)
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