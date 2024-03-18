package viewmodel.records

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import model.UserModel
import userinterface.ISubscriber
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URI
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class RecordsViewModel(val model: UserModel) : ISubscriber {
    val foodRecords: MutableList<Pair<String, List<Int>>> = mutableListOf()
    val drinkRecords: MutableList<Pair<String, List<Int>>> = mutableListOf()
    val exerciseRecords: MutableList<Pair<String, List<Int>>> = mutableListOf()

    init {
        model.subscribe(this)
    }

    override fun update() {
    }

    fun addRecord(item: String, amount: String, type: String) {
        val nutritionInfo = calculateCalorieofNewIntake(item, amount, type)
        val calorie = nutritionInfo[0]
        val fat = nutritionInfo[1]
        val protein = nutritionInfo[2]
        val sugar = nutritionInfo[3]
        insertRecord(item, amount, type, calorie, fat, protein, sugar)
        updateView()
        model.notifySubscribers()
    }

    private fun connect(): Connection? {
        var connection: Connection? = null
        try {
            val appDataDir = System.getProperty("user.home") + File.separator + ".CalorieWise"
            val dbPath = "$appDataDir${File.separator}data.db"
            val url = "jdbc:sqlite:$dbPath"

//            val url = "jdbc:sqlite:src/main/kotlin/data/data.db"
            connection = DriverManager.getConnection(url)
            println("Connection is valid.")
        } catch (e: SQLException) {
            println(e.message)
        }
        return connection
    }

    private fun Connection.insertRecord(
        username: String,
        item: String,
        amount: Int,
        type: String,
        calorie: Int, fat: Int, protein: Int, sugar: Int
//      TODO: add date: Int
    ): Int {
        try {
            val stmt = createStatement()
            stmt.executeUpdate(
                "INSERT INTO Records (username, recordItem, recordType, recordAmount, recordCalorie, recordFat, recordProtein, recordSugar) " +
                        "VALUES ('${username}', '${item}', '${type}', ${amount}, ${calorie}, ${fat}, ${protein}, ${sugar});"
            )
            stmt.close()
            return 1
        } catch (exception: Exception) {
            println(exception)
            return -1
        }
    }

    private fun Connection.updateView(): Int {
        try {
            val query = prepareStatement(
                "SELECT recordItem, recordAmount, recordType, recordCalorie, recordFat, recordProtein, recordSugar FROM Records WHERE username = '${model.email}';"
            )
            val result = query.executeQuery()
            while (result.next()) {
                val resultType = result.getString("recordType")
                val resultItem = result.getString("recordItem")
                val resultAmount = result.getInt("recordAmount")
                val resultCalorie = result.getInt("recordCalorie")
                val resultProtein = result.getInt("recordProtein")
                val resultSugar = result.getInt("recordSugar")
                val resultFat = result.getInt("recordFat")
                when (resultType) {
                    "food" -> {
                        foodRecords.add(Pair(resultItem, listOf(resultAmount, resultCalorie)))
                        model.calorieTaken += resultCalorie
                        model.fatTaken += resultFat
                        model.proteinTaken += resultProtein
                        model.sugarTaken += resultSugar
                    }

                    "drink" -> {
                        drinkRecords.add(Pair(resultItem, listOf(resultAmount, resultCalorie)))
                        model.calorieTaken += resultCalorie
                        model.fatTaken += resultFat
                        model.proteinTaken += resultProtein
                        model.sugarTaken += resultSugar
                    }

                    "exercise" -> {
                        exerciseRecords.add(Pair(resultItem, listOf(resultAmount, resultCalorie)))
                        model.calorieBurned += resultCalorie
                    }

                    else -> assert(false)
                }

            }
            result.close()
            query.close()
            return 1
        } catch (exception: Exception) {
            println(exception)
            return -1
        }
    }

    private fun insertRecord(
        item: String,
        amount: String,
        type: String,
        calorie: Int,
        fat: Int,
        protein: Int,
        sugar: Int
    ) {
        val connection = connect()
        val insertSuccessCode =
            connection?.insertRecord(model.email, item, amount.toInt(), type, calorie, fat, protein, sugar)
        assert(insertSuccessCode == 1)
    }

    private fun calculateCalorieofNewIntake(item: String, amount: String, type: String): List<Int> {
        var calorie: Int = 0
        var fat: Int = 0
        var protein: Int = 0
        var sugar: Int = 0
        if (type == "food" || type == "drink") {
            val apikey = "Z42q0ajL9oxbsMkdlrIylA==a3w338OixwhNmIEt"
            val query = "${amount}g%20${item}".replace(" ", "%20")
            val uri = URI("https://api.api-ninjas.com/v1/nutrition?x-api-key=${apikey}&query=${query}")
            val url = uri.toURL()
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("accept", "application/json")
            val responseStream: InputStream = connection.inputStream
            val mapper = ObjectMapper()
            val root: JsonNode = mapper.readTree(responseStream)
            if (root.size() == 0 || root.isNull) {
                calorie = 70
            } else {
                calorie = root[0]["calories"].asInt()
                fat = root[0]["fat_total_g"].asInt()
                protein = root[0]["protein_g"].asInt()
                sugar = root[0]["sugar_g"].asInt()
            }
            model.fatTaken += fat
            model.proteinTaken += protein
            model.sugarTaken += sugar
            model.calorieTaken += calorie
        } else {
            calorie = 100
            model.calorieBurned += calorie
        }
        return listOf(calorie, fat, protein, sugar)
    }

    fun updateView() {
        foodRecords.clear()
        drinkRecords.clear()
        exerciseRecords.clear()
        val connection = connect()
        val updateViewSuccessCode = connection?.updateView()
        assert(updateViewSuccessCode == 1)
    }
}