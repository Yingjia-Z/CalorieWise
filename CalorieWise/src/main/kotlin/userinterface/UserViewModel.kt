package userinterface

import androidx.compose.runtime.mutableStateOf
import model.UserModel
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class UserViewModel(val model: UserModel) : ISubscriber {
    var isInDarkTheme = mutableStateOf(false)

    init {
        model.subscribe(this)
    }

    override fun update() {
        isInDarkTheme.value = model.isInDarkTheme
    }

    fun connect(): Connection? {
        var connection: Connection? = null
        try {
            val appDataDir = System.getProperty("user.home") + File.separator + ".CalorieWise"
            val dbPath = "$appDataDir${File.separator}data.db"
            val url = "jdbc:sqlite:$dbPath"

//            val url = "jdbc:sqlite:src/main/kotlin/data/data.db"
            connection = DriverManager.getConnection(url)
            println("Connection2 is valid.")
        } catch (e: SQLException) {
            println(e.message)
        }
        return connection
    }

    private fun Connection.updateModel(): Int {
        try {
            val query = prepareStatement(
                "SELECT height, weight, gender, goalWeight, age FROM BasicInfo WHERE username = ?;"
            )
            query.setString(1, model.email)

            val result = query.executeQuery()

            if (result.next()) {
                val userHeight = result.getInt("height")
                val userWeight = result.getInt("weight")
                val userGender = result.getString("gender")
                val userGoalWeight = result.getInt("goalWeight")
                val userAge = result.getInt("age")

                model.height = userHeight
                model.weight = userWeight
                model.gender = userGender
                model.goalWeight = userGoalWeight
                model.age = userAge
            }
            result.close()
            query.close()
            return 1
        } catch (exception: Exception) {
            println(exception)
            return -1
        }
    }

    fun updateModel() {
        model.height = 0
        model.weight = 0
        model.gender = ""
        model.goalWeight = 0
        model.age = 0
        val connection = connect()
        val updateViewSuccessCode = connection?.updateModel()
        assert(updateViewSuccessCode == 1)
    }
}