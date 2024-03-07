package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import userinterface.analysis.AnalysisPageView
import userinterface.basicInfo.BasicInformationPage
import userinterface.basicInfo.recommendation.RecommendationPage
import userinterface.composables.Screens
import userinterface.homepage.HomepageView
import userinterface.login.LoginPageView
import userinterface.records.RecordsView
import userinterface.records.addDrink.AddDrinkView
import userinterface.records.addExercise.AddExerciseView
import userinterface.records.addFood.AddFoodView
import userinterface.theme.AppColors
import userinterface.theme.MyApplicationTheme
import viewmodel.analysis.AnalysisPageViewModel
import viewmodel.basicInfo.BasicInformationViewModel
import viewmodel.homepage.HomepageViewModel
import viewmodel.login.LoginPageViewModel
import viewmodel.records.RecordsViewModel
import viewmodel.records.addDrink.AddDrinkViewModel
import viewmodel.records.addExercise.AddExerciseViewModel
import viewmodel.records.addFood.AddFoodViewModel

@Composable
fun UserView(userViewModel: UserViewModel) {
    val viewModel by remember { mutableStateOf(userViewModel) }

    val loginPageViewModel = LoginPageViewModel(viewModel.model)
    val homepageViewModel = HomepageViewModel(viewModel.model)
    val addFoodViewModel = AddFoodViewModel(viewModel.model)
    val addDrinkViewModel = AddDrinkViewModel(viewModel.model)
    val addExerciseViewModel = AddExerciseViewModel(viewModel.model)
    val basicInformationViewModel = BasicInformationViewModel(viewModel.model)
    val recordsViewModel = RecordsViewModel(viewModel.model)
    val analysisPageViewModel = AnalysisPageViewModel(viewModel.model)

    // Maintain the current screen using rememberSaveable
    var currentScreen by rememberSaveable { mutableStateOf(Screens.Login.screen) }
    var focusedButton by rememberSaveable { mutableStateOf("") }
    var recordsOverlay = false
    var recordsType = ""

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
                .background(
                    color = if (focusedButton == imageRes) AppColors.LightViolet else Color.Transparent,
                    shape = RoundedCornerShape(50)
                )
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "graph icon",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }

    MyApplicationTheme {
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
                        SidebarImageButton("icons/Home.png") { currentScreen = Screens.Homepage.screen }
                        SidebarImageButton("icons/Graph.png") { currentScreen = Screens.Analysis.screen }
                        SidebarImageButton("icons/My-Nutrition.png") { currentScreen = Screens.Records.screen }
                        SidebarImageButton("icons/Profile.png") { currentScreen = Screens.BasicInfo.screen }
                    }
                }
            }

            // Content area
            when (currentScreen) {
                Screens.Homepage.screen -> {
                    HomepageView(
                        homepageViewModel,
                        {
                            currentScreen = Screens.Records.screen
                            recordsOverlay = true
                            recordsType = "food"
                        },
                        {
                            currentScreen = Screens.Records.screen
                            recordsOverlay = true
                            recordsType = "drink"
                        },
                        {
                            currentScreen = Screens.Records.screen
                            recordsOverlay = true
                            recordsType = "exercise"
                        }
                    )
                    focusedButton = "icons/Home.png"
                }
                Screens.BasicInfo.screen -> {
                    BasicInformationPage(
                        basicInformationViewModel,
                        { currentScreen = Screens.Recommendation.screen })
                    focusedButton = "icons/Profile.png"
                }

                Screens.Recommendation.screen -> {
                    RecommendationPage(
                        basicInformationViewModel,
                        { currentScreen = Screens.Homepage.screen })
                    focusedButton = "icons/Profile.png"
                }

                Screens.Login.screen -> LoginPageView(
                    loginPageViewModel,
                    { currentScreen = Screens.BasicInfo.screen })

                Screens.Records.screen -> {
                    RecordsView(
                        recordsViewModel,
                        { currentScreen = Screens.AddFood.screen },
                        { currentScreen = Screens.AddDrink.screen },
                        { currentScreen = Screens.AddExercise.screen },
                        recordsOverlay,
                        recordsType
                    )
                    focusedButton = "icons/My-Nutrition.png"
                }

                Screens.AddFood.screen -> AddFoodView(addFoodViewModel)
                Screens.AddDrink.screen -> AddDrinkView(addDrinkViewModel)
                Screens.AddExercise.screen -> AddExerciseView(addExerciseViewModel)

                Screens.Analysis.screen -> {
                    AnalysisPageView(analysisPageViewModel)
                    focusedButton = "icons/Graph.png"
                }
            }
            recordsOverlay = false
            recordsType = ""
        }
    }
}
