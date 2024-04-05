package userinterface.basicInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import userinterface.composables.defaultHeightUnits
import userinterface.composables.defaultWeightUnits
import viewmodel.basicInfo.BasicInformationViewModel

@Composable
fun BasicInformationPage(
    basicInformationViewModel: BasicInformationViewModel,
    onNextStepClick: () -> Unit
) {
    val viewModel by remember { mutableStateOf(basicInformationViewModel) }

    val ageFocusRequester = remember { FocusRequester() }
    val genderFocusRequester = remember { FocusRequester() }
    val heightFocusRequester = remember { FocusRequester() }
    val weightFocusRequester = remember { FocusRequester() }
    val goalWeightFocusRequester = remember { FocusRequester() }

    val handleEnterEvent: (KeyEvent) -> Boolean = { keyEvent ->
        if (keyEvent.key == Key.Enter && keyEvent.type == KeyEventType.KeyUp) {
            viewModel.updateBasicInformation()
            onNextStepClick()
            true
        } else {
            false
        }
    }

    LaunchedEffect(Unit) {
        ageFocusRequester.requestFocus()
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().onKeyEvent(handleEnterEvent)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            //basic information text fields

            Text(
                text = "BASIC INFORMATION",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(30.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))

            //now use lay column to get a column of text fields, compose 5-1 lazy example

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    TextField(
                        value = viewModel.displayAge.value,
                        onValueChange = {
                            viewModel.displayAge.value = it.trim()
                            viewModel.age.value = it.trim()
                        },
                        label = { Text("AGE") },
                        modifier = Modifier
                            .focusRequester(ageFocusRequester)
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.key == Key.Tab && keyEvent.type == KeyEventType.KeyUp) {
                                    genderFocusRequester.requestFocus()
                                    true
                                } else false
                            }
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = viewModel.displayGender.value.uppercase(),
                        onValueChange = {
                            viewModel.displayGender.value = it
                            viewModel.gender.value = it
                        },
                        label = { Text("SEX(M/F)") },
                        modifier = Modifier
                            .focusRequester(genderFocusRequester)
                            .onKeyEvent {
                                if (it.key == Key.Tab && it.type == KeyEventType.KeyUp) {
                                    heightFocusRequester.requestFocus()
                                    true
                                } else false
                            }
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        value = viewModel.displayHeight.value,
                        onValueChange = {
                            viewModel.displayHeight.value = it
                            viewModel.height.value = defaultHeightUnits(
                                viewModel.displayHeight.value,
                                viewModel.heightUnits.value
                            ).toString()
                        },
                        label = { Text("HEIGHT (${viewModel.heightUnits.value})") },
                        modifier = Modifier
                            .focusRequester(heightFocusRequester)
                            .onKeyEvent {
                                if (it.key == Key.Tab && it.type == KeyEventType.KeyUp) {
                                    weightFocusRequester.requestFocus()
                                    true
                                } else false
                            }
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        value = viewModel.displayWeight.value,
                        onValueChange = {
                            viewModel.displayWeight.value = it
                            viewModel.weight.value = defaultWeightUnits(
                                viewModel.displayWeight.value,
                                viewModel.weightUnits.value
                            ).toString()
                        },
                        label = { Text("WEIGHT (${viewModel.weightUnits.value})") },
                        modifier = Modifier
                            .focusRequester(weightFocusRequester)
                            .onKeyEvent {
                                if (it.key == Key.Tab && it.type == KeyEventType.KeyUp) {
                                    goalWeightFocusRequester.requestFocus()
                                    true
                                } else false
                            }
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        value = viewModel.displayGoalWeight.value,
                        onValueChange = {
                            viewModel.displayGoalWeight.value = it
                            viewModel.goalWeight.value = defaultWeightUnits(
                                viewModel.displayGoalWeight.value,
                                viewModel.weightUnits.value
                            ).toString()
                        },
                        label = { Text("GOAL WEIGHT (${viewModel.weightUnits.value})") },
                        modifier = Modifier
                            .focusRequester(goalWeightFocusRequester)
                            .onKeyEvent {
                                if (it.key == Key.Tab && it.type == KeyEventType.KeyUp) {
                                    ageFocusRequester.requestFocus()
                                    true
                                } else false
                            }
                    )

                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    viewModel.updateBasicInformation()
                    onNextStepClick()
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Next Step")
            }

        }
    }
}