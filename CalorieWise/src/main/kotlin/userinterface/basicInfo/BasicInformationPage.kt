package userinterface.basicInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.UserController

@Composable
fun BasicInformationPage(userViewModel: BasicInformationViewModel, userController: UserController,
                         onNextStepClick: () -> Unit) {
    val viewModel by remember { mutableStateOf(userViewModel) }
    val controller by remember { mutableStateOf(userController) }
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
                horizontalAlignment= Alignment.CenterHorizontally


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
                    TextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("HEIGHT(cm)") },
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text("WEIGHT(kg)") },
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = goalWeight,
                        onValueChange = { goalWeight = it },
                        label = { Text("GOAL WEIGHT(kg)") },
                    )

                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    userViewModel.setGender(gender)
                    userViewModel.setAge(age)
                    userViewModel.setHeight(height)
                    userViewModel.setWeight(weight)
                    userViewModel.setGoalWeight(goalWeight)
                    val c = userViewModel.calculateCalroieIntake()
                    userViewModel.setCalorieIntake(c)
                    val w = userViewModel.calculateWaterIntake()
                    userViewModel.setwaterIntake(w)
                    val e = userViewModel.calculateExercise()
                    userViewModel.setExerciseIntake(e)
                    userViewModel.updateBasicInformation(gender, age, height, weight, goalWeight, c, w, e)
                    onNextStepClick()
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Next Step")
            }
        }
    }
}