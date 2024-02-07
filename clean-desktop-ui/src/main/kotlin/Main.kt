import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import controller.UserController
import model.UserModel
import userinterface.*

fun main() = application {
    val userModel = UserModel()
    val userViewModel = UserViewModel(userModel)
    val userController = UserController(userModel)

    Window(
        title = "MVC Demo",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(275.dp, 200.dp)
        ),
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
        UserView(userViewModel, userController)
    }
}
