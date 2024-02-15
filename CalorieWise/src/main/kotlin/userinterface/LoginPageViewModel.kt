package userinterface

import androidx.compose.runtime.mutableStateOf
import model.UserModel

class LoginPageViewModel(val model: UserModel) : ISubscriber {
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    init {
        model.subscribe(this)
    }

    override fun update() {
        email.value = model.email
        password.value = model.password
    }
}