package userinterface.records

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import userinterface.composables.*
import viewmodel.records.RecordsViewModel

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
            Text(text = name)
            Text(text = "$calorie kcal", color = Color.Gray)
        }
        Text(
            text = quantity,
            modifier = Modifier.align(Alignment.CenterVertically).padding(5.dp)
        )
    }
}

@Composable
fun RecordsView(
    recordsViewModel: RecordsViewModel,
    overlayVisible: Boolean, recordType: String
) {
    val viewModel by remember { mutableStateOf(recordsViewModel) }
    var overlayVisible by remember { mutableStateOf(overlayVisible) }
    var recordType by remember { mutableStateOf(recordType) }
    var recordItem by remember { mutableStateOf("") }
    var recordAmount by remember { mutableStateOf("") }
    var foodRecords by remember { mutableStateOf(viewModel.foodRecords) }
    var drinkRecords by remember { mutableStateOf(viewModel.drinkRecords) }
    var exerciseRecords by remember { mutableStateOf(viewModel.exerciseRecords) }

    viewModel.updateView()
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
                        Text(text = "Add Food", style = MaterialTheme.typography.h5)
                        Button(
                            onClick = {
                                overlayVisible = true
                                recordType = "food"
                            },
                            shape = CircleShape
                        ) {
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
                        Text(text = "Add Drinks", style = MaterialTheme.typography.h5)
                        Button(
                            onClick = {
                                overlayVisible = true
                                recordType = "drink"
                            },
                            shape = CircleShape
                        ) {
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
                        Text(text = "Add Exercise", style = MaterialTheme.typography.h5)
                        Button(
                            onClick = {
                                overlayVisible = true
                                recordType = "exercise"
                            },
                            shape = CircleShape
                        ) {
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
                        /* TODO: Calculate the calories of food/water/exercise */
                        Card(modifier = Modifier.fillMaxSize().weight(1f)) {
                            Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
                                Text(text = "Food", style = MaterialTheme.typography.h5)
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    foodRecords.forEach { record ->
                                        val displayQuantity =
                                            updateFoodUnits(record.second[0], viewModel.foodUnits.value)
                                        HistoryEntry(
                                            record.first,
                                            record.second[1].toString(),
                                            displayQuantity.toString() + " ${viewModel.foodUnits.value}"
                                        )
                                    }
                                }
                            }
                        }
                        Card(modifier = Modifier.fillMaxSize().weight(1f)) {
                            Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
                                Text(text = "Drink", style = MaterialTheme.typography.h5)
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    drinkRecords.forEach { record ->
                                        val displayQuantity =
                                            updateDrinkUnits(record.second[0], viewModel.drinkUnits.value)
                                        HistoryEntry(
                                            record.first,
                                            record.second[1].toString(),
                                            displayQuantity.toString() + " ${viewModel.drinkUnits.value}"
                                        )
                                    }
                                }
                            }
                        }
                        Card(modifier = Modifier.fillMaxSize().weight(1f)) {
                            Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
                                Text(text = "Exercise", style = MaterialTheme.typography.h5)
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    exerciseRecords.forEach { record ->
                                        val displayQuantity =
                                            updateExerciseUnits(record.second[0].toInt(), viewModel.exerciseUnits.value)
                                        HistoryEntry(
                                            record.first,
                                            record.second[1].toString(),
                                            displayQuantity.toString() + " ${viewModel.exerciseUnits.value}"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Overlay area
        if (overlayVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colors.background.copy(alpha = 0.8f), shape = RectangleShape),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            TextField(
                                value = recordItem,
                                onValueChange = { recordItem = it },
                                label = {
                                    var inputTypePrompt =
                                        when (recordType) {
                                            "food" -> "food you consumed"
                                            "drink" -> "drink you consumed"
                                            "exercise" -> "exercise you performed"
                                            else -> assert(false)
                                        }
                                    Text("Please enter the name of the $inputTypePrompt")
                                },
                                modifier = Modifier.width(450.dp)
                            )

                            var displayAmount by remember { mutableStateOf("") }

                            TextField(
                                value = displayAmount,
                                onValueChange = {
                                    displayAmount = it
                                    recordAmount =
                                        when (recordType) {
                                            "food" -> defaultFoodUnits(
                                                displayAmount,
                                                viewModel.foodUnits.value
                                            ).toString()

                                            "drink" -> defaultDrinkUnits(
                                                displayAmount,
                                                viewModel.drinkUnits.value
                                            ).toString()

                                            "exercise" -> defaultExerciseUnits(
                                                displayAmount,
                                                viewModel.exerciseUnits.value
                                            ).toString()

                                            else -> assert(false).toString()
                                        }
                                },
                                label = {
                                    var inputAmountPrompt =
                                        when (recordType) {
                                            "food" -> "Amount (${viewModel.foodUnits.value})"
                                            "drink" -> "Amount (${viewModel.drinkUnits.value})"
                                            "exercise" -> "Duration (${viewModel.exerciseUnits.value})"
                                            else -> assert(false)
                                        }
                                    Text("$inputAmountPrompt")
                                },
                                modifier = Modifier.width(200.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(
                                onClick = {
                                    recordsViewModel.addRecord(recordItem, recordAmount, recordType)
                                    recordItem = ""
                                    recordAmount = ""
                                    overlayVisible = false
                                },
                                modifier = Modifier.width(100.dp)
                            ) {
                                Text("Add")
                            }
                            Button(
                                onClick = {
                                    overlayVisible = false
                                },
                                modifier = Modifier.width(100.dp)
                            ) {
                                Text("Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}