package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import controller.LoginPageController
import controller.UserController
import model.UserModel

enum class LoginPageViewEvent {
    EmailEvent,
    PasswordEvent
}

@Composable
fun LoginPageView(loginPageViewModel: LoginPageViewModel, loginPageController: LoginPageController) {
    val viewModel by remember { mutableStateOf(loginPageViewModel) }
    val controller by remember { mutableStateOf(loginPageController) }

    MaterialTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource("SignIn.jpg"),
                    contentDescription = "SignIn Image",
                    modifier = Modifier.size(150.dp)
                        .padding(0.dp,0.dp, 0.dp,0.dp),
                    contentScale = ContentScale.Fit)

                //Spacer(modifier = Modifier.height(25.dp))

                TextField(
                    viewModel.email.value,
                    label = {Text("E-mail: ")},
                    onValueChange = { controller.invoke(LoginPageViewEvent.EmailEvent, it) })

                Spacer(modifier = Modifier.height(25.dp))

                TextField(
                    viewModel.password.value,
                    label = {Text("Password: ")},
                    onValueChange = { controller.invoke(LoginPageViewEvent.PasswordEvent, it) })

                Spacer(modifier = Modifier.height(25.dp))

                Button(onClick = {}) {
                    Text("Sign In")
                }

                Spacer(modifier = Modifier.height(25.dp))

                Text("Don't have an account?")
                Text("Sign Up")
            }
        }

    }
}