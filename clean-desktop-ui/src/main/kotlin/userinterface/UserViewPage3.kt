package userinterface

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import controller.UserController


@Composable
fun UserViewPage3(userViewModel: UserViewModel, userController: UserController) {
    val viewModel by remember { mutableStateOf(userViewModel) }
    val controller by remember { mutableStateOf(userController) }

    MaterialTheme {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            TextField(viewModel.firstname.value,
                label = { Text("First Name: ") },
                onValueChange = { controller.invoke(ViewEvent.FirstNameEvent, it) })
            TextField(viewModel.lastname.value,
                label = { Text("Last Name: ") },
                onValueChange = { controller.invoke(ViewEvent.LastNameEvent, it) })

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
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

@Composable
fun CombinedDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().paddingFromBaseline(125.dp, 50.dp),
        verticalArrangement = Arrangement.spacedBy(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Based on the information you provided, your CalorieWise recommendations are: ",
            style = MaterialTheme.typography.h4,
            modifier = modifier.width(1000.dp)
        )
        Row {
            Text(
                text = "Daily Calorie Intake",
                style = MaterialTheme.typography.h5,
                modifier = modifier.align(alignment = Alignment.CenterVertically).padding(10.dp),
                color = Color.Red
            )
            Text(
                text = "1890 Cals",
                style = MaterialTheme.typography.body1,
                modifier = modifier.align(alignment = Alignment.CenterVertically).padding(10.dp)
            )
        }
        Row {
            Text(
                text = "Daily Water Intake",
                style = MaterialTheme.typography.h5,
                modifier = modifier.align(alignment = Alignment.CenterVertically).padding(10.dp),
                color = Color.Red
            )
            Text(
                text = "3 Litres",
                style = MaterialTheme.typography.body1,
                modifier = modifier.align(alignment = Alignment.CenterVertically).padding(10.dp)
            )
        }
        Row {
            Text(
                text = "Daily Exercise",
                style = MaterialTheme.typography.h5,
                modifier = modifier.align(alignment = Alignment.CenterVertically).padding(10.dp),
                color = Color.Red,
            )
            Text(
                text = "30 Min",
                style = MaterialTheme.typography.body1,
                modifier = modifier.align(alignment = Alignment.CenterVertically).padding(10.dp)
            )
        }
        Button(
            modifier = modifier, onClick = {
                println("A different button clicked.")
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White)
        ) {
            Text("Next Step")
        }

    }

}

@Composable
fun ScaffoldDemo() {
    val materialBlue700 = Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text("TopAppBar") }, backgroundColor = materialBlue700) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Text("X")
            }
        },
        drawerContent = { Text(text = "drawerContent") },
        content = { Text("BodyContent") },
        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } })
}

