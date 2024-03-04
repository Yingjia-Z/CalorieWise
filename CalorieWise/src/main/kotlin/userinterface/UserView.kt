package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import controller.*
import controller.addDrink.AddDrinkController
import controller.addExercise.AddExerciseController
import controller.addFood.AddFoodController
import controller.homepage.HomepageController
import controller.login.LoginPageController
import userinterface.composables.Screens
import userinterface.addDrink.AddDrinkViewModel
import userinterface.addExercise.AddExerciseViewModel
import userinterface.addFood.AddFoodView
import userinterface.addFood.AddFoodViewModel
import userinterface.basicInfo.BasicInformationPage
import userinterface.basicInfo.BasicInformationViewModel
import userinterface.homepage.HomepageView
import userinterface.homepage.HomepageViewModel
import userinterface.login.LoginPageView
import userinterface.login.LoginPageViewModel
import userinterface.recommendation.RecommendationPage

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
    val basicInformationViewModel = BasicInformationViewModel(viewModel.model)
    // Maintain the current screen using rememberSaveable
    var currentScreen by rememberSaveable { mutableStateOf(Screens.Login.screen) }
    var focusedButton by rememberSaveable { mutableStateOf("") }

    @Composable
    fun SidebarImageButton(imageRes: String, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(25.dp, 15.dp, 15.dp, 0.dp)
                .clickable {
                    onClick()
                    focusedButton = imageRes
                }
                .background(color = if(focusedButton == imageRes) Color.LightGray else Color.Transparent,
                    shape = RoundedCornerShape(50))
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "graph icon",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }

    MaterialTheme {
        // Content area
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Check if the current screen is not the LoginPage, then display the sidebar
            if (currentScreen != Screens.Login.screen) {
                // Sidebar with buttons to navigate to different screens
                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    item {
                        SidebarImageButton("Home.png") { currentScreen = Screens.Homepage.screen }
                        SidebarImageButton("Graph.png") { currentScreen = Screens.AddFood.screen }
                        SidebarImageButton("My-Nutrition.png") { /* TODO: Add onclick function */ }
                        SidebarImageButton("Profile.png") { currentScreen = Screens.BasicInfo.screen }
                    }
                }
            }

            // Content area
            when (currentScreen) {
                Screens.Homepage.screen -> HomepageView(homepageViewModel, homepageController)
                Screens.BasicInfo.screen -> BasicInformationPage(basicInformationViewModel, userController, { currentScreen = Screens.Recommendation.screen })
                Screens.Recommendation.screen -> RecommendationPage(basicInformationViewModel, userController, { currentScreen = Screens.Homepage.screen })
                Screens.Login.screen -> LoginPageView(loginPageViewModel, loginPageController, { currentScreen = Screens.BasicInfo.screen })
                Screens.AddFood.screen -> AddFoodView(addFoodViewModel, addFoodController) /* TODO: Set default to AddFoodPage, switch to AddDrinkPage & AddExercisePage missing */

                /* TODO: Food Recommendation Page missing */
            }
        }
    }
}
