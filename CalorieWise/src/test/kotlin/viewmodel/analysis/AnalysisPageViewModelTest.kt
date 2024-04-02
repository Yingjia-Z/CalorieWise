package viewmodel.analysis

import org.junit.jupiter.api.Assertions.*
import model.UserModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*;
import viewmodel.homepage.HomepageViewModel
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.test.Test

class AnalysisPageViewModelTest{
    private lateinit var userModel: UserModel
    private lateinit var analysisViewModel: AnalysisPageViewModel

    @BeforeEach
    fun setUp() {
        userModel = mock(UserModel::class.java)
        analysisViewModel = AnalysisPageViewModel(userModel)
    }

    @Test
    fun testSugarEatenUpdate() {
        `when`(userModel.sugarTaken).thenReturn(20)
        analysisViewModel.update()
        assertEquals(20, analysisViewModel.sugarEaten.value)
    }

    @Test
    fun testFatEatenUpdate() {
        `when`(userModel.fatTaken).thenReturn(10)
        analysisViewModel.update()
        assertEquals(10, analysisViewModel.fatEaten.value)
    }

}