import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.UserModel
import userinterface.UserView
import userinterface.UserViewModel
import userinterface.theme.MyApplicationTheme

fun main() = application {
    DatabaseUtils.copyDatabaseIfNotExists()

    val userModel = UserModel()
    val userViewModel = UserViewModel(userModel)

    Window(
        title = "CalorieWise",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(1366.dp, 768.dp)
        ),
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
        MyApplicationTheme {
            UserView(userViewModel)
        }
    }
}