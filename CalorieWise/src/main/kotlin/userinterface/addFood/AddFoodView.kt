package userinterface.addFood

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import controller.addFood.AddFoodController


enum class AddFoodEvent {
    FirstNameEvent,
    LastNameEvent,
    UppercaseEvent,
    LowercaseEvent,
    ResetEvent
}


@Composable
fun FoodEntry(name: String, quantity: String, controller: AddFoodController) {
//    val controller by remember { mutableStateOf(addFoodController) }
    Card(
        modifier = Modifier.width(360.dp),
        backgroundColor = Color.White
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.border(1.dp, Color.LightGray),
        ) {
            Text(
                text = "$name $quantity",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f).padding(5.dp)
            )
            Button(
                onClick = {
                    controller.invoke(AddFoodEvent.LowercaseEvent, null)
                },
                shape = CircleShape,
                modifier = Modifier.padding(5.dp)
            ) {
                Text("+")
            }
        }
    }

}

@Composable
fun AddFoodView(addFoodViewModel: AddFoodViewModel, addFoodController: AddFoodController) {
    val viewModel by remember { mutableStateOf(addFoodViewModel) }
    val controller by remember { mutableStateOf(addFoodController) }

    Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource("icons/FoodIcon.png"),
                contentDescription = "Food Page Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            LazyColumn(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                item {
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(
                            onClick = { controller.invoke(AddFoodEvent.UppercaseEvent, null) },
                            modifier = Modifier.width(118.dp)
                        ) {
                            Text("New")
                        }
                        Button(
                            onClick = { controller.invoke(AddFoodEvent.UppercaseEvent, null) },
                            modifier = Modifier.width(118.dp)
                        ) {
                            Text("Favourite")
                        }
                        Button(
                            onClick = { controller.invoke(AddFoodEvent.UppercaseEvent, null) },
                            modifier = Modifier.width(118.dp)
                        ) {
                            Text("Recent")
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))

                    FoodEntry("Noodle", "20 g", controller)
                    FoodEntry("Noodle", "20 g", controller)
                }
            }

        }

}