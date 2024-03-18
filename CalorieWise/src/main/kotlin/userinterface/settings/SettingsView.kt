package userinterface.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import viewmodel.settings.SettingsViewModel

enum class SettingsViewEvent {
    SignOutEvent, ChangePasswordEvent, ChangeThemeEvent, UnitConversionEvent, FavoritesEditingEvent
}

@Composable
fun SettingsView(
    settingsViewModel: SettingsViewModel, onSignOutSuccess: () -> Unit
) {
    val viewmodel by remember { mutableStateOf(settingsViewModel) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text("Settings", style = MaterialTheme.typography.subtitle2)

            LazyVerticalGrid(
//                modifier = Modifier.padding(10.dp),
                contentPadding = PaddingValues(20.dp),
                columns = GridCells.Fixed(3)
            ) {

                // Logout
                item {
                    Card(modifier = Modifier.padding(20.dp)) {
                        Button(
                            onClick = {
                                viewmodel.invoke(SettingsViewEvent.SignOutEvent, 1)
                                if (!viewmodel.loggedin) {
                                    onSignOutSuccess()
                                } else {
                                    viewmodel.settingsMessage.value = "Something went wrong, you are still logged in."
                                }
                            },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text("Log out")
                        }
                    }
                }

                // Change password
                item {
                    Card(modifier = Modifier.padding(20.dp)) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // TODO: maybe using text instead?
                            TextField(
                                viewmodel.email.value,
                                label = { Text("E-mail: ") },
                                enabled = false,
                                onValueChange = {},
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource("icons/EmailIcon.png"),
                                        contentDescription = "Email",
                                        tint = Color.Gray,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            )

                            TextField(
                                viewmodel.password.value,
                                label = { Text("Password: ") },
                                onValueChange = {
                                    viewmodel.passwordEdited = true
                                    viewmodel.password.value = it
                                    viewmodel.settingsMessage.value = ""
                                },
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

                            Button(
                                onClick = {
                                    viewmodel.invoke(SettingsViewEvent.ChangePasswordEvent, viewmodel.password.value)
                                    viewmodel.passwordEdited = false
                                },
                                enabled = viewmodel.passwordEdited
                            ) {
                                Text("Save")
                            }
                        }
                    }
                }

                // Theme switch
                item {
                    Card(modifier = Modifier.padding(20.dp)) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Light/Dark Theme Switch")
                            Switch(
                                checked = viewmodel.isInDarkTheme.value,
                                onCheckedChange = {
                                    viewmodel.isInDarkTheme.value = it
                                    viewmodel.invoke(SettingsViewEvent.ChangeThemeEvent, 1)
                                }
                            )
                            if (viewmodel.isInDarkTheme.value) {
                                Text("Dark Theme")
                            } else {
                                Text("Light Theme")
                            }
                        }
                    }
                }

                // Unit conversion
                item {
                    Card(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Unit conversion",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                // Favorite editing
                item {
                    Card(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Favorite editing",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            Text(viewmodel.settingsMessage.value, color = Color.Red)
        }
    }
}