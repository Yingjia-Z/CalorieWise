package userinterface.basicInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import userinterface.composables.defaultHeightUnits
import userinterface.composables.defaultWeightUnits
import viewmodel.basicInfo.BasicInformationViewModel

@Composable
fun BasicInformationPage(
    basicInformationViewModel: BasicInformationViewModel,
    onNextStepClick: () -> Unit
) {
    val viewModel by remember { mutableStateOf(basicInformationViewModel) }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var goalWeight by remember { mutableStateOf("") }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            //basic information text fields

            Text(
                text = "BASIC INFORMATION",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(30.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))

            //now use lay column to get a column of text fields, compose 5-1 lazy example

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    TextField(
                        value = gender,
                        onValueChange = { gender = it },
                        label = { Text("SEX(M/F)") },
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = age,
                        onValueChange = { age = it },
                        label = { Text("AGE") },
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    var displayHeight by remember { mutableStateOf("") }
                    TextField(
                        value = displayHeight,
                        onValueChange = {
                            displayHeight = it
                            height = defaultHeightUnits(displayHeight, viewModel.heightUnits.value).toString()
                        },
                        label = { Text("HEIGHT (${viewModel.heightUnits.value})") },
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    var displayWeight by remember { mutableStateOf("") }
                    TextField(
                        value = displayWeight,
                        onValueChange = {
                            displayWeight = it
                            weight = defaultWeightUnits(displayWeight, viewModel.weightUnits.value).toString()
                        },
                        label = { Text("WEIGHT (${viewModel.weightUnits.value})") },
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    var displayGoalWeight by remember { mutableStateOf("") }
                    TextField(
                        value = displayGoalWeight,
                        onValueChange = {
                            displayGoalWeight = it
                            goalWeight = defaultWeightUnits(displayGoalWeight, viewModel.weightUnits.value).toString()
                        },
                        label = { Text("GOAL WEIGHT (${viewModel.weightUnits.value})") },
                    )

                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    viewModel.setGender(gender)
                    viewModel.setAge(age)
                    viewModel.setHeight(height)
                    viewModel.setWeight(weight)
                    viewModel.setGoalWeight(goalWeight)
                    val c = viewModel.calculateCalroieIntake()
                    viewModel.setCalorieIntake(c)
                    val w = viewModel.calculateWaterIntake()
                    viewModel.setwaterIntake(w)
                    val e = viewModel.calculateExercise()
                    viewModel.setExerciseIntake(e)
                    val f = viewModel.calculateFatIntake()
                    viewModel.setFatIntake(f)
                    val s = viewModel.calculateSugarIntake()
                    viewModel.setFatIntake(s)
                    val p = viewModel.calculateProteinIntake()
                    viewModel.setFatIntake(p)
                    viewModel.updateBasicInformation(
                        gender,
                        age,
                        height,
                        weight,
                        goalWeight,
                        c,
                        w,
                        e,
                        f,
                        s,
                        p
                    )
                    onNextStepClick()
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Next Step")
            }
        }
    }
}