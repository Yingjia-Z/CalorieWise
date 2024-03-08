import java.io.File
import java.io.FileOutputStream

object DatabaseUtils {
    fun copyDatabaseIfNotExists() {
        val appDataDir = System.getProperty("user.home") + File.separator + ".CalorieWise"
        val dbPath = "$appDataDir${File.separator}data.db"

        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            dbFile.parentFile.mkdirs() // Ensure the directory exists
            DatabaseUtils::class.java.getResourceAsStream("/data.db").use { input ->
                if (input != null) {
                    FileOutputStream(dbFile).use { output ->
                        input.copyTo(output)
                    }
                } else {
                    throw IllegalArgumentException("Resource not found: data.db")
                }
            }
        }
    }
}
