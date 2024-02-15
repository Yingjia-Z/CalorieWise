import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import controller.*
import model.LoginPageModel
import model.UserModel
import userinterface.*

fun main() = application {
    val userModel = UserModel()
    val userViewModel = UserViewModel(userModel)
    val userController = UserController(userModel)
    val loginPageModel = LoginPageModel()
    val loginPageViewModel = LoginPageViewModel(loginPageModel)
    val loginPageController = LoginPageController(loginPageModel)
    val homepageViewModel = HomepageViewModel(userModel)
    val homepageController = HomepageController(userModel)
    val addFoodViewModel = AddFoodViewModel(userModel)
    val addFoodController = AddFoodController(userModel)
    val addDrinkViewModel = AddDrinkViewModel(userModel)
    val addDrinkController = AddDrinkController(userModel)
    val addExerciseViewModel = AddExerciseViewModel(userModel)
    val addExerciseController = AddExerciseController(userModel)


    Window(
        title = "MVC Demo",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(1366.dp, 768.dp)

        ),
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
//        UserView(userViewModel, userController)
        //LoginPageView(loginPageViewModel, loginPageController)
//        BasicInformationPage(userViewModel, userController)
//        HomepageView(homepageViewModel, homepageController)
//        UIPage3(userViewModel, userController)
//        AddFoodView(addFoodViewModel, addFoodController)
//        AddDrinkView(addDrinkViewModel, addDrinkController)
        AddExerciseView(addExerciseViewModel, addExerciseController)
    }
}