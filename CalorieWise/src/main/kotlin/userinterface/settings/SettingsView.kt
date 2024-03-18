package userinterface.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import viewmodel.settings.SettingsViewModel

enum class SettingsViewEvent {
    SignOutEvent, ChangePasswordEvent, ChangeThemeEvent, UnitConversionEvent, FavoritesEditingEvent
}

@Composable
fun SettingsView(
    settingsViewModel: SettingsViewModel, onSignOutSuccess: () -> Unit,
    overlayVisible: Boolean, settingsType: String
) {
    val viewmodel by remember { mutableStateOf(settingsViewModel) }
    var overlayVisible by remember { mutableStateOf(overlayVisible) }
    var settingsType by remember { mutableStateOf(settingsType) }

    @Composable
    fun changePasswordPrompt() {
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
                label = { Text("New Password: ") },
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
                enabled = viewmodel.passwordEdited,
                modifier = Modifier.width(100.dp)
            ) {
                Text("Save")
            }
        }
    }

    @Composable
    fun themeSwitch() {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewmodel.isInDarkTheme.value) {
                Text("Switch to Light Theme")
            } else {
                Text("Switch to Dark Theme")
            }
            Switch(
                checked = viewmodel.isInDarkTheme.value,
                onCheckedChange = {
                    viewmodel.isInDarkTheme.value = it
                    viewmodel.invoke(SettingsViewEvent.ChangeThemeEvent, 1)
                }
            )
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Settings", style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.height(40.dp))

            LazyColumn(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
            ) {

                // Change password
                item {
                    Button(
                        onClick = {
                            overlayVisible = true
                            settingsType = "password"
                        },
                        modifier = Modifier.width(300.dp)
                    ) {
                        Text(
                            "Change Password",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                // Theme switch
                item {
                    Button(
                        onClick = {
                            overlayVisible = true
                            settingsType = "theme"
                        },
                        modifier = Modifier.width(300.dp)
                    ) {
                        Text(
                            "Change Theme",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                // Unit conversion
                item {
                    Button(
                        onClick = {
                            overlayVisible = true
                            settingsType = "unit"
                        },
                        modifier = Modifier.width(300.dp)
                    ) {
                        Text(
                            "Change Units",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                // Favorite editing
                item {
                    Button(
                        onClick = {
                            overlayVisible = true
                            settingsType = "favorite"
                        },
                        modifier = Modifier.width(300.dp)
                    ) {
                        Text(
                            "Edit Favorites",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                // Logout
                item {
                    Button(
                        onClick = {
                            viewmodel.invoke(SettingsViewEvent.SignOutEvent, 1)
                            if (!viewmodel.loggedin) {
                                onSignOutSuccess()
                            } else {
                                viewmodel.settingsMessage.value = "Something went wrong, you are still logged in."
                            }
                        },
                        modifier = Modifier.width(300.dp)
                    ) {
                        Text(
                            "Log out",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }

        // Overlay area
        if (overlayVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colors.background.copy(alpha = 0.8f), shape = RectangleShape),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        when (settingsType) {
                            "password" -> changePasswordPrompt()
                            "theme" -> themeSwitch()
                            "unit" -> TODO()
                            "favorite" -> TODO()
                            else -> assert(false)
                        }

                        Button(
                            onClick = {
                                overlayVisible = false
                                settingsType = ""
                                viewmodel.settingsMessage.value = ""
                            },
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text("Back")
                        }

                        Text(
                            viewmodel.settingsMessage.value,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}