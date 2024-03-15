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
        val calorie = calculateCalorieofNewIntake(item, amount, type)
        insertRecord(item, amount, type, calorie)
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
        calorie: Int
//      TODO: add date: Int
    ): Int {
        try {
            val stmt = createStatement()
            stmt.executeUpdate(
                "INSERT INTO Records (username, recordItem, recordType, recordAmount, recordCalorie) " +
                        "VALUES ('${username}', '${item}', '${type}', ${amount}, ${calorie});"
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
                "SELECT recordItem, recordAmount, recordType, recordCalorie FROM Records WHERE username = '${model.email}';"
            )
            val result = query.executeQuery()
            while (result.next()) {
                val resultType = result.getString("recordType")
                val resultItem = result.getString("recordItem")
                val resultAmount = result.getInt("recordAmount")
                val resultCalorie = result.getInt("recordCalorie")
                when (resultType) {
                    "food" -> foodRecords.add(Pair(resultItem, listOf(resultAmount, resultCalorie)))
                    "drink" -> drinkRecords.add(Pair(resultItem, listOf(resultAmount, resultCalorie)))
                    "exercise" -> exerciseRecords.add(Pair(resultItem, listOf(resultAmount, resultCalorie)))
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

    private fun insertRecord(item: String, amount: String, type: String, calorie: Int) {
        val connection = connect()
        val insertSuccessCode =
            connection?.insertRecord(model.email, item, amount.toInt(), type, calorie)
        assert(insertSuccessCode == 1)
    }

    private fun calculateCalorieofNewIntake(item: String, amount: String, type: String): Int {
        val calorie: Int
        if (type == "food" || type == "drink") {
            val apikey = "Z42q0ajL9oxbsMkdlrIylA==a3w338OixwhNmIEt"
            val query = "${amount}g%20${item}"
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
                val fat = root[0]["fat_total_g"].asInt()
                val protein = root[0]["protein_g"].asInt()
                val sugar = root[0]["sugar_g"].asInt()
                model.fatTaken += fat
                model.proteinTaken += protein
                model.sugerTaken += sugar
            }
            model.calorieTaken += calorie
        } else {
            calorie = -100
            model.calorieBurned += calorie
        }
        return calorie
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