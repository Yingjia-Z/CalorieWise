package userinterface

import model.UserModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.test.Test

internal class UserViewModelTest {
    private lateinit var model: UserModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var mockConnection: Connection
    private lateinit var mockPreparedStatement: PreparedStatement
    private lateinit var mockResultSet: ResultSet
    private lateinit var spyUserViewModel: UserViewModel

    @BeforeEach
    fun setUp() {
        model = UserModel()
        userViewModel = UserViewModel(model)

        mockConnection = mock(Connection::class.java)
        mockPreparedStatement = mock(PreparedStatement::class.java)
        mockResultSet = mock(ResultSet::class.java)
        spyUserViewModel =
            spy(userViewModel) //OpenAI. (2024). ChatGPT (March 28 version) [Large language model]. https://chat.openai.com/chat

        //`when`(userViewModel.connect()).thenReturn(mockConnection)
        doReturn(mockConnection).`when`(spyUserViewModel).connect()
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true)

        `when`(mockResultSet.getInt("height")).thenReturn(180)
        `when`(mockResultSet.getInt("weight")).thenReturn(99)
        `when`(mockResultSet.getString("gender")).thenReturn("M")
        `when`(mockResultSet.getInt("goalWeight")).thenReturn(88)
        `when`(mockResultSet.getInt("age")).thenReturn(25)
    }

    @Test
    fun testUpdateModel() {
        model.email = "test@test.com"
        spyUserViewModel.updateModel()

        // Assert that the model has been updated with the expected values
        assertEquals(180, model.height)
        assertEquals(99, model.weight)
        assertEquals("M", model.gender)
        assertEquals(88, model.goalWeight)
        assertEquals(25, model.age)

    }

    @Test
    fun testSubscribeAndNotify() {
        model.isInDarkTheme = true
        assertEquals(true, userViewModel.isInDarkTheme.value)
    }


}