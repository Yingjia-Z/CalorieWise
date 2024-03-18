package viewmodel.settings

import androidx.compose.runtime.mutableStateOf
import model.UserModel
import userinterface.ISubscriber
import userinterface.settings.SettingsViewEvent
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class SettingsViewModel(val model: UserModel) : ISubscriber {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    var loggedin = true
    var passwordEdited = false
    var settingsMessage = mutableStateOf("")
    var isInDarkTheme = mutableStateOf(false)

    init {
        model.subscribe(this)
        isInDarkTheme.value = model.isInDarkTheme
        settingsMessage.value = ""
    }

    private fun signOut() {
        model.loggedIn = false

        // Clear session data
        model.email = ""
        model.password = ""
        model.gender = ""
        model.age = -1
        model.height = -1
        model.weight = -1
        model.goalWeight = -1
        model.recommendedCaloryIntake = 0
        model.recommendedWaterIntake = 0
        model.recommendedExercistIntake = 0
        model.recommendedFatIntake = 0
        model.recommendedProteinIntake = 0
        model.recommendedSugarIntake = 0
        model.calorieTaken = 0
        model.calorieBurned = 0
        model.waterTaken = 0
        model.fatTaken = 0
        model.proteinTaken = 0
        model.sugerTaken = 0
    }

    private fun connect(): Connection? {
        var connection: Connection? = null
        try {
            val appDataDir = System.getProperty("user.home") + File.separator + ".CalorieWise"
            val dbPath = "$appDataDir${File.separator}data.db"
            val url = "jdbc:sqlite:$dbPath"

            connection = DriverManager.getConnection(url)
            println("Connection is valid.")
        } catch (e: SQLException) {
            println(e.message)
        }
        return connection
    }

    private fun Connection.updatePassword(
        newPassword: String
    ): Int {
        try {
            val stmt = createStatement()
            stmt.executeUpdate(
                "UPDATE Users SET password = '${newPassword}' WHERE username = '${model.email}';"
            )
            stmt.close()
            return 1
        } catch (exception: Exception) {
            println(exception)
            return -1
        }
    }

    private fun updatePassword(newPassword: String) {
        val connection = connect()
        val updatePasswordSuccessCode = connection?.updatePassword(newPassword)
        assert(updatePasswordSuccessCode == 1)
        model.password = newPassword
        settingsMessage.value = "You've changed your password. "
        model.notifySubscribers()
    }

    fun invoke(event: SettingsViewEvent, value: Any?) {
        when (event) {
            SettingsViewEvent.SignOutEvent -> signOut()
            SettingsViewEvent.ChangePasswordEvent -> updatePassword(value as String)
            SettingsViewEvent.ChangeThemeEvent -> model.isInDarkTheme = !model.isInDarkTheme
            SettingsViewEvent.UnitConversionEvent -> TODO()
            SettingsViewEvent.FavoritesEditingEvent -> TODO()
        }
    }

    override fun update() {
        email.value = model.email
        password.value = model.password
        loggedin = model.loggedIn
        isInDarkTheme.value = model.isInDarkTheme
    }
}