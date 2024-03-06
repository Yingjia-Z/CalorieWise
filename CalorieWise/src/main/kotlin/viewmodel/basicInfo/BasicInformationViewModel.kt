package viewmodel.basicInfo

import model.UserModel
import userinterface.ISubscriber
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.math.roundToInt

class BasicInformationViewModel(val model: UserModel) : ISubscriber {
    private var gender: String = ""
    private var age: String = ""
    private var height: String = ""
    private var weight: String = ""
    private var goalWeight: String = ""
    var calorie: Int = 0
    var waterIntake: Int = 0
    var exercise: Int = 0

    init {
        model.subscribe(this)
    }

    //get the latest data from the model.
    override fun update() {
        gender = model.gender
        age = model.age.toString()
        height = model.height.toString()
        goalWeight = model.goalWeight.toString()
        calorie = model.calorieIntake
        waterIntake = model.waterIntake
        exercise = model.exerciseIntake
    }

    //update the model with information from user input.
    fun updateBasicInformation(
        gender: String,
        age: String,
        height: String,
        weight: String,
        goalWeight: String,
        calorieIntake: Int,
        waterIntake: Int,
        exerciseIntake: Int
    ) {
        model.gender = gender
        model.age = age.toInt()
        model.height = height.toInt()
        model.weight = weight.toInt()
        model.goalWeight = goalWeight.toInt()
        model.calorieIntake = calorieIntake
        model.waterIntake = waterIntake
        model.exerciseIntake = exerciseIntake
        insertOrAddUserBasicInfo(model.email, model.height, model.weight, model.goalWeight, model.age, gender)
        model.notifySubscribers()
    }

    fun setGender(value: String) {
        gender = value
    }

    fun setAge(value: String) {
        age = value
    }

    fun setHeight(value: String) {
        height = value
    }

    fun setWeight(value: String) {
        weight = value
    }

    fun setGoalWeight(value: String) {
        goalWeight = value
    }

    fun setCalorieIntake(value: Int) {
        calorie = value
    }

    fun setwaterIntake(value: Int) {
        waterIntake = value
    }

    fun setExerciseIntake(value: Int) {
        exercise = value
    }

    fun calculateCalroieIntake(): Int {
        //for women: BMR = 655 + (9.6 × body weight in kg) + (1.8 × body height in cm) - (4.7 × age in years);
        // for men: BMR = 66 + (13.7 × weight in kg) + (5 × height in cm) - (6.8 × age in years).
        var calories = 0.0
        val weight = weight.toDouble()
        val height = height.toDouble()
        val age = age.toDouble()

        if (gender.equals("M")) {
            calories = 66 + (13.7 * weight) + (5 * height) - (6.8 * age)
        } else if (gender.equals("F")) {
            calories = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)
        }
        return calories.roundToInt()
    }

    fun calculateWaterIntake(): Int {
        //multuply by 67%, gives answer in ounces
        var water = 0.0
        val weight = weight.toDouble()

        return (weight * 0.67).roundToInt()
    }

    fun calculateExercise(): Int {/*TODO: no formula found???*/
        return 0
    }

    fun connect(): Connection? {
        var connection: Connection? = null
        try {
            val url = "jdbc:sqlite:src/main/kotlin/data/data.db"
            connection = DriverManager.getConnection(url)
            println("Connection is valid.")
        } catch (e: SQLException) {
            println(e.message)
        }
        return connection
    }

    private fun Connection.checkUserExistInTable(username: String, tablename: String): Int {
        var exist = -1
        try {
            val query =
                prepareStatement("SELECT COUNT(*) AS user_count FROM ${tablename} WHERE username = '${username}';")
            val result = query.executeQuery()
            result.next()
            exist = result.getInt("user_count")
            result.close()
            query.close()
            return exist
        } catch (exception: Exception) {
            println(exception)
            return -1
        }
    }

    private fun Connection.insertOrUpdateBasicInfo(
        username: String,
        height: Int,
        weight: Int,
        goalWeight: Int,
        age: Int,
        gender: String,
        exist: Int
    ): Int {
        if (exist == 0) {
            try {
                val stmt = createStatement()
                stmt.executeUpdate(
                    "INSERT INTO BasicInfo (username, height, weight, goalWeight, age, gender) " +
                            "VALUES ('${username}', ${height}, ${weight}, ${goalWeight}, ${age}, '${gender}');"
                )
                stmt.close()
                return 1
            } catch (exception: Exception) {
                println(exception)
                return -1
            }
        } else if (exist == 1) {
            try {
                val stmt = createStatement()
                stmt.executeUpdate(
                    "UPDATE BasicInfo SET height = ${height}, weight = ${weight}, " +
                            "goalWeight = ${goalWeight}, age = ${age}, gender = '${gender}' WHERE username = '${username}';"
                )
                stmt.close()
                return 1
            } catch (exception: Exception) {
                println(exception)
                return -1
            }
        } else {
            return -1
        }
    }

    private fun insertOrAddUserBasicInfo(
        username: String,
        height: Int,
        weight: Int,
        goalWeight: Int,
        age: Int,
        gender: String
    ) {
        val connection = connect()
        val exist = connection?.checkUserExistInTable(username, "BasicInfo")
        val insertOrUpdateSuccessCode =
            connection?.insertOrUpdateBasicInfo(username, height, weight, goalWeight, age, gender, exist!!)
        assert(insertOrUpdateSuccessCode == 1)
    }

}


