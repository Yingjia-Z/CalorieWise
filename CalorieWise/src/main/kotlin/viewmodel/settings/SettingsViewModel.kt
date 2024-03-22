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
    var heightUnits = mutableStateOf("")
    var weightUnits = mutableStateOf("")
    var foodUnits = mutableStateOf("")
    var drinkUnits = mutableStateOf("")
    var exerciseUnits = mutableStateOf("")

    init {
        model.subscribe(this)
        isInDarkTheme.value = model.isInDarkTheme
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
        model.recommendedCalorieIntake = 0
        model.recommendedWaterIntake = 0
        model.recommendedExerciseIntake = 0
        model.recommendedFatIntake = 0
        model.recommendedProteinIntake = 0
        model.recommendedSugarIntake = 0
        model.calorieTaken = 0
        model.calorieBurned = 0
        model.waterTaken = 0
        model.fatTaken = 0
        model.proteinTaken = 0
        model.sugarTaken = 0
        println("Data cleared")
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

    private fun updateUnits(type: String, units: String) {
        when (type) {
            "height" -> model.heightUnits = units
            "weight" -> model.weightUnits = units
            "food" -> model.foodUnits = units
            "drink" -> model.drinkUnits = units
            "exercise" -> model.exerciseUnits = units
            else -> assert(false)
        }
    }

    fun invoke(event: SettingsViewEvent, value1: Any?, value2: Any?) {
        when (event) {
            SettingsViewEvent.SignOutEvent -> signOut()
            SettingsViewEvent.ChangePasswordEvent -> updatePassword(value1 as String)
            SettingsViewEvent.ChangeThemeEvent -> model.isInDarkTheme = !model.isInDarkTheme
            SettingsViewEvent.UnitsConversionEvent -> updateUnits(value1 as String, value2 as String)
            SettingsViewEvent.FavoritesEditingEvent -> TODO()
        }
    }

    override fun update() {
        email.value = model.email
        password.value = model.password
        loggedin = model.loggedIn
        isInDarkTheme.value = model.isInDarkTheme
        heightUnits.value = model.heightUnits
        weightUnits.value = model.weightUnits
        foodUnits.value = model.foodUnits
        drinkUnits.value = model.drinkUnits
        exerciseUnits.value = model.exerciseUnits
    }
}