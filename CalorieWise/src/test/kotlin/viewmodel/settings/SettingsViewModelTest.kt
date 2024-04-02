package viewmodel.settings

import org.junit.jupiter.api.Assertions.*
import model.UserModel
import userinterface.settings.SettingsViewEvent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*;
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.test.Test

class SettingsViewModelTest{
    private lateinit var model: UserModel
    private lateinit var viewModel: SettingsViewModel

    @BeforeEach
    fun setUp() {
        model = UserModel()
        viewModel = SettingsViewModel(model)
    }


    @Test
    fun testPasswordUpdate() {
        val newPassword = "newpassword"

        viewModel.invoke(SettingsViewEvent.ChangePasswordEvent, newPassword,null)
        assertEquals(newPassword,model.password)
    }

    @Test
    fun testUpdateUnits() {
        val newHeightUnits = "inches"
        val newWeightUnits = "pounds"

        viewModel.invoke(SettingsViewEvent.UnitsConversionEvent, "height", newHeightUnits)
        viewModel.invoke(SettingsViewEvent.UnitsConversionEvent, "weight", newWeightUnits)

        assertEquals(newHeightUnits, model.heightUnits)
        assertEquals(newWeightUnits, model.weightUnits)
    }

    @Test
    fun testClearingDataOnSignout() {
        viewModel.invoke(SettingsViewEvent.SignOutEvent, "", "")

        assert(!model.loggedIn)
        assertEquals(model.email,"")
        assertEquals(model.password, "")

    }

}