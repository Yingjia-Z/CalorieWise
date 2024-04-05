import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager(
    hostname: String = "34.31.38.46:3306",
    schemaName: String = "calorie-wise"
) {
    private val jdbcUrl: String = "jdbc:mysql://$hostname/$schemaName?user=root"
    private val connection: Connection = establishConnection()

    // Establish a database connection
    private fun establishConnection(): Connection {
        println("Establishing connection!")
        Class.forName("com.mysql.cj.jdbc.Driver")
        return DriverManager.getConnection(jdbcUrl)
    }

    // Get the connection to the database
    fun getConnection(): Connection {
        if (connection.isClosed) {
            establishConnection()
        }
        println("Connection Successful!")
        return connection
    }

    // Test the database connection
    fun testConnection(): Boolean {
        return try {
            getConnection().use {
                println("Connection to database successful")
            }
            true
        } catch (e: Exception) {
            println("Failed to connect to the database: ${e.message}")
            false
        }
    }
}