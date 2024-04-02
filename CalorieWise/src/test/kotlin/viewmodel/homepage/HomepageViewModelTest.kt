package viewmodel.homepage

import org.junit.jupiter.api.Assertions.*
import model.UserModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*;
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.test.Test

class HomepageViewModelTest{
    private lateinit var userModel: UserModel
    private lateinit var homepageViewModel: HomepageViewModel

    @BeforeEach
    fun setUp() {
        userModel = mock(UserModel::class.java)
        homepageViewModel = HomepageViewModel(userModel)
    }

    @Test
    fun testCalorieEatenUpdate() {
        `when`(userModel.calorieTaken).thenReturn(100)
        homepageViewModel.update()
        assertEquals(100, homepageViewModel.calorieEaten.value)
    }

}