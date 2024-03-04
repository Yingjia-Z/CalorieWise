package userinterface.recommendation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import controller.UserController
import userinterface.basicInfo.BasicInformationViewModel


@Composable
fun RecommendationPage(basicInformationViewModel: BasicInformationViewModel, userController: UserController,
                       onSignInClick: () -> Unit) {
    val viewModel by remember { mutableStateOf(basicInformationViewModel) }
    val controller by remember { mutableStateOf(userController) }

    Column(
        modifier = Modifier.fillMaxSize().paddingFromBaseline(125.dp, 50.dp),
        verticalArrangement = Arrangement.spacedBy(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Based on the information you provided, your CalorieWise recommendations are: ",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.width(1000.dp)
        )
        Row {
            Text(
                text = "Daily Calorie Intake",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(10.dp),
                color = Color.Red
            )
            Text(
                text = "${basicInformationViewModel.calorie} Cals",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(10.dp)
            )
        }
        Row {
            Text(
                text = "Daily Water Intake",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(10.dp),
                color = Color.Red
            )
            Text(
                text = "${basicInformationViewModel.waterIntake} ounces",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(10.dp)
            )
        }
        Row {
            Text(
                text = "Daily Exercise",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(10.dp),
                color = Color.Red,
            )
            Text(
                text = "30 Min",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(10.dp)
            )
        }
        Button(
            modifier = Modifier, onClick = { onSignInClick() }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White)
        ) {
            Text("Next Step")
        }

    }

}
