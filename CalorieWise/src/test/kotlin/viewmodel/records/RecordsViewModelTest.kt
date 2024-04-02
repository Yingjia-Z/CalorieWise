package viewmodel.records

import model.UserModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*;
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement
import kotlin.test.Test

class RecordsViewModelTest{
    private lateinit var model: UserModel
    private lateinit var viewModel: RecordsViewModel

    @BeforeEach
    fun setUp() {
        model = UserModel()
        model.foodUnits = "grams"
        model.drinkUnits = "milliliters"
        model.exerciseUnits = "minutes"
        viewModel = RecordsViewModel(model)
    }

    @Test
    fun testUpdateViewModelState() {
        viewModel.update()

        assertEquals("grams", viewModel.foodUnits.value)
        assertEquals("milliliters", viewModel.drinkUnits.value)
        assertEquals("minutes", viewModel.exerciseUnits.value)
    }


}