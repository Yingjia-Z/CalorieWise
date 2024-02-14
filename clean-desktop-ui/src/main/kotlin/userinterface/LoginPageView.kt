package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    painter = painterResource("SignIn.png"),
                    contentDescription = "SignIn Image",
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit)

                TextField(
                    viewModel.email.value,
                    label = {Text("E-mail: ")},
                    onValueChange = { controller.invoke(LoginPageViewEvent.EmailEvent, it) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource("EmailIcon.png"),
                            contentDescription = "Email",
                            tint = Color.Gray,
                            modifier = Modifier.size(30.dp)
                        )
                    })

                Spacer(modifier = Modifier.height(25.dp))

                TextField(
                    viewModel.password.value,
                    label = {Text("Password: ")},
                    onValueChange = { controller.invoke(LoginPageViewEvent.PasswordEvent, it) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource("PassWordIcon.png"),
                            contentDescription = "Password",
                            tint = Color.Gray,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource("ViewIcon.png"),
                            contentDescription = "View",
                            tint = Color.Gray,
                            modifier = Modifier.size(30.dp)
                        )
                    })

                Spacer(modifier = Modifier.height(25.dp))

                Button(onClick = {}) {
                    Text("Sign In")
                }

                Spacer(modifier = Modifier.height(25.dp))

                Text("Don't have an account?")
                TextButton( onClick = {} ) {
                    Text("Sign Up")
                }
            }
        }

    }
}