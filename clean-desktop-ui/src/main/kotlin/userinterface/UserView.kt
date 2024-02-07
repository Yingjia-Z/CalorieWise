package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import controller.UserController
import model.UserModel

enum class ViewEvent {
    FirstNameEvent,
    LastNameEvent,
    UppercaseEvent,
    LowercaseEvent,
    ResetEvent
}

@Composable
fun UserView(userViewModel: UserViewModel, userController: UserController) {
    val viewModel by remember { mutableStateOf(userViewModel) }
    val controller by remember { mutableStateOf(userController) }

    MaterialTheme {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            TextField(
                viewModel.firstname.value,
                label = {Text("First Name: ")},
                onValueChange = { controller.invoke(ViewEvent.FirstNameEvent, it) })
            TextField(
                viewModel.lastname.value,
                label = {Text("Last Name: ")},
                onValueChange = { controller.invoke(ViewEvent.LastNameEvent, it) })

            Row (horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    controller.invoke(ViewEvent.UppercaseEvent, null)
                }) {
                    Text("UPPER")
                }
                Button(onClick = {
                    controller.invoke(ViewEvent.LowercaseEvent, null)
                }) {
                    Text("lower")
                }
                Button(onClick = {
                    controller.invoke(ViewEvent.ResetEvent, null)
                }) {
                    Text("Reset")
                }
            }
        }
    }
}