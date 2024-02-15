package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import controller.*

@Composable
fun UserView(userViewModel: UserViewModel, userController: UserController) {
    val viewModel by remember { mutableStateOf(userViewModel) }
    val controller by remember { mutableStateOf(userController) }

    val loginPageViewModel = LoginPageViewModel(viewModel.model)
    val loginPageController = LoginPageController(viewModel.model)
    val homepageViewModel = HomepageViewModel(viewModel.model)
    val homepageController = HomepageController(viewModel.model)
    val addFoodViewModel = AddFoodViewModel(viewModel.model)
    val addFoodController = AddFoodController(viewModel.model)
    val addDrinkViewModel = AddDrinkViewModel(viewModel.model)
    val addDrinkController = AddDrinkController(viewModel.model)
    val addExerciseViewModel = AddExerciseViewModel(viewModel.model)
    val addExerciseController = AddExerciseController(viewModel.model)

    // Maintain the current screen using rememberSaveable
    var currentScreen by rememberSaveable { mutableStateOf(model.Screen.LoginPage) }
    MaterialTheme {
        // Content area
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Check if the current screen is not the LoginPage, then display the sidebar
            if (currentScreen != model.Screen.LoginPage) {
                // Sidebar with buttons to navigate to different screens
                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    item {
                        Image(
                            painter = painterResource("Home.jpg"),
                            contentDescription = "home icon",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(25.dp, 15.dp, 15.dp, 0.dp)
                                .clickable { currentScreen = model.Screen.HomePage },
                            contentScale = ContentScale.Fit,
                        )
                        Image(
                            painter = painterResource("Graph.jpg"),
                            contentDescription = "graph icon",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(25.dp, 15.dp, 15.dp, 0.dp)
                                .clickable { currentScreen = model.Screen.AddPage },
                            contentScale = ContentScale.Fit,
                        )
                        Image(
                            painter = painterResource("My-Nutrition.jpg"),
                            contentDescription = "nutrition icon",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(25.dp, 15.dp, 15.dp, 0.dp)
                                .clickable { /* TODO: Add onclick function */ },
                            contentScale = ContentScale.Fit,
                        )
                        Image(
                            painter = painterResource("Profile.jpg"),
                            contentDescription = "profile icon",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(25.dp, 15.dp, 15.dp, 0.dp)
                                .clickable { currentScreen = model.Screen.BasicInfoPage },
                            contentScale = ContentScale.Fit,
                        )
                    }
                }
            }

            // Content area
            when (currentScreen) {
                model.Screen.HomePage -> HomepageView(homepageViewModel, homepageController)
                model.Screen.BasicInfoPage -> BasicInformationPage(userViewModel, userController, { currentScreen = model.Screen.IntakePage })
                model.Screen.IntakePage -> UIPage3(userViewModel, userController)
                model.Screen.LoginPage -> LoginPageView(loginPageViewModel, loginPageController, { currentScreen = model.Screen.BasicInfoPage })
                model.Screen.AddPage -> AddFoodView(addFoodViewModel, addFoodController) /* TODO: Set default to AddFoodPage, switch to AddDrinkPage & AddExercisePage missing */

                /* TODO: Food Recommendation Page missing */
            }
        }
    }
}