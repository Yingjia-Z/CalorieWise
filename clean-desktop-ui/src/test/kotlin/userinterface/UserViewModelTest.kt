package userinterface

import model.UserModel
import kotlin.test.Test

internal class UserViewModelTest {
    @Test
    fun setup() {
        val model = UserModel()
        val viewModel = UserViewModel(model)
        assert(viewModel.model == model)
    }

    @Test
    fun subscription() {
        val model = UserModel()
        val viewModel = UserViewModel(model)

        model.firstname = "Adam"
        assert(viewModel.firstname.value == model.firstname)

        model.lastname = "Ant"
        assert(viewModel.lastname.value == model.lastname)
    }
}