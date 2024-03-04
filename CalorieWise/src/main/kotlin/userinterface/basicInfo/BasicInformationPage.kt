package userinterface.basicInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            //basic information text fields
            Image(
                painter = painterResource("BasicInformation.jpg"),
                contentDescription = "BasicInformation Image",
                modifier = Modifier.size(250.dp)
                    .padding(15.dp, 0.dp, 0.dp, 0.dp),
                contentScale = ContentScale.Fit,

                )
            //now use lay column to get a column of text fields, compose 5-1 lazy example

            LazyColumn(
                //modifier = Modifier.padding(4.dp),
                modifier = Modifier.weight(0.5f)


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
        }
        Image(
            painter = painterResource("NextStep.jpg"),
            contentDescription = "NextStepButton Image",
            modifier = Modifier
                .size(210.dp)
                .align(Alignment.BottomCenter)
                .padding(0.dp, 80.dp, 0.dp, 0.dp)
                .clickable {
                    userViewModel.setGender(gender)
                    userViewModel.setAge(age)
                    userViewModel.setHeight(height)
                    userViewModel.setWeight(weight)
                    userViewModel.setGoalWeight(goalWeight)
                    val c = userViewModel.calculateCalroieIntake();
                    userViewModel.setCalorieIntake(c)
                    val w = userViewModel.calculateWaterIntake();
                    userViewModel.setwaterIntake(w)
                    val e = userViewModel.calculateExercise();
                    userViewModel.setExerciseIntake(e)
                    userViewModel.updateBasicInformation(gender, age, height, weight, goalWeight, c,w,e)
                    onNextStepClick() },
            contentScale = ContentScale.Fit,
        )
    }
}