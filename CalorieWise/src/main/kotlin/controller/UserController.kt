package controller

import androidx.compose.runtime.Composable
import model.Screen
import model.UserModel
import userinterface.*

class UserController(val model: UserModel, val viewModel: UserViewModel,
                     val loginViewModel: LoginPageViewModel, val loginPageController: LoginPageController,
                     val homeViewModel: HomepageViewModel, val homeController: HomepageController) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: ViewEvent, value: Any?) {
        when(event) {
            ViewEvent.FirstNameEvent -> model.firstname = value as String
            ViewEvent.LastNameEvent -> model.lastname = value as String
            ViewEvent.UppercaseEvent -> {
                model.firstname = model.firstname.uppercase()
                model.lastname = model.lastname.uppercase()
            }
            ViewEvent.LowercaseEvent -> {
                model.firstname = model.firstname.lowercase()
                model.lastname = model.lastname.lowercase()
            }
            ViewEvent.ResetEvent -> {
                model.firstname = ""
                model.lastname = ""
            }
        }
    }

    @Composable
    fun SwitchScreen(screen: model.Screen) {
        when(screen) {
            Screen.LoginPage -> LoginPageView(loginViewModel, loginPageController, this)
            Screen.BasicInfoPage -> BasicInformationPage(viewModel,this)
            Screen.HomePage -> HomepageView(homeViewModel,homeController)
            Screen.IntakePage -> UIPage3(viewModel, this)
        }
    }
}