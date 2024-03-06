package userinterface.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import userinterface.composables.Appname
import viewmodel.login.LoginPageViewModel

enum class LoginPageViewEvent {
    EmailEvent, PasswordEvent, SignInEvent
}

@Composable
fun LoginPageView(
    loginPageViewModel: LoginPageViewModel, onSignInSuccess: () -> Unit
) {
    val viewmodel by remember { mutableStateOf(loginPageViewModel) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Appname()

            Spacer(modifier = Modifier.height(25.dp))

            TextField(
                viewmodel.email.value,
                label = { Text("E-mail: ") },
                onValueChange = { viewmodel.invoke(LoginPageViewEvent.EmailEvent, it) },
                leadingIcon = {
                    Icon(
                        painter = painterResource("icons/EmailIcon.png"),
                        contentDescription = "Email",
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                viewmodel.password.value,
                label = { Text("Password: ") },
                onValueChange = { viewmodel.invoke(LoginPageViewEvent.PasswordEvent, it) },
                leadingIcon = {
                    Icon(
                        painter = painterResource("icons/PasswordIcon.png"),
                        contentDescription = "Password",
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource("icons/ViewIcon.png"),
                        contentDescription = "View",
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(45.dp))

            Button(
                onClick = {
                    viewmodel.invoke(LoginPageViewEvent.SignInEvent, 1)
                    if (viewmodel.loggedin) {
                        onSignInSuccess()
                    } else {
                        viewmodel.loginMessage.value = "Wrong Password. Please try again. "
                    }
                }) {
                Text("Log In / Sign Up")
            }

            Spacer(modifier = Modifier.height(45.dp))

            Text(viewmodel.loginMessage.value, color = Color.Red)
//            TextButton(
//                onClick = {},
//                colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
//            ) {
//                Text("Sign Up")
//            }
        }
    }
}