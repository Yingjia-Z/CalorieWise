package userinterface.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import controller.login.LoginPageController

enum class LoginPageViewEvent {
    EmailEvent, PasswordEvent, SignInEvent
}

@Composable
fun LoginPageView(
    loginPageViewModel: LoginPageViewModel, loginPageController: LoginPageController, onSignInClick: () -> Unit
) {
    val viewModel by remember { mutableStateOf(loginPageViewModel) }
    val controller by remember { mutableStateOf(loginPageController) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource("SignIn.png"),
                contentDescription = "SignIn Image",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )

            TextField(
                viewModel.email.value,
                label = { Text("E-mail: ") },
                onValueChange = { controller.invoke(LoginPageViewEvent.EmailEvent, it) },
                leadingIcon = {
                    Icon(
                        painter = painterResource("EmailIcon.png"),
                        contentDescription = "Email",
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red, focusedLabelColor = Color.Red, cursorColor = Color.Red
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            TextField(
                viewModel.password.value,
                label = { Text("Password: ") },
                onValueChange = { controller.invoke(LoginPageViewEvent.PasswordEvent, it) },
                leadingIcon = {
                    Icon(
                        painter = painterResource("PasswordIcon.png"),
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
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red, focusedLabelColor = Color.Red, cursorColor = Color.Red
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    controller.invoke(LoginPageViewEvent.SignInEvent, 1)
                    if (viewModel.loggedin) {
                        onSignInClick()
                    }
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red, contentColor = Color.White
                )
            ) {
                Text("Sign In")
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text("Don't have an account?")
            TextButton(onClick = {}, colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)) {
                Text("Sign Up")
            }
        }
    }
}