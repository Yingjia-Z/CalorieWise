package controller

import model.LoginPageModel
import model.UserModel
import userinterface.LoginPageViewEvent

class LoginPageController(val model: LoginPageModel) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: LoginPageViewEvent, value: Any?) {
        when(event) {
            LoginPageViewEvent.EmailEvent -> model.email = value as String
            LoginPageViewEvent.PasswordEvent -> model.password = value as String
        }
    }
}