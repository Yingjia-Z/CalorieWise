import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import controller.HomepageController
import controller.UserController
import model.UserModel
import userinterface.*

fun main() = application {
    val userModel = UserModel()
    val userViewModel = UserViewModel(userModel)
    val userController = UserController(userModel)
    val homepageViewModel = HomepageViewModel(userModel)
    val homepageController = HomepageController(userModel)

    Window(
        title = "MVC Demo",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(1366.dp, 768.dp)

        ),
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
//        BasicInformationPage(userViewModel, userController)
        UserView(userViewModel, userController)
//        HomepageView(homepageViewModel, homepageController)
//        UIPage3(userViewModel, userController)
    }
}
