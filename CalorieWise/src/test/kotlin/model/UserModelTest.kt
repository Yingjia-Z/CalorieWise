package model

import kotlin.test.Test

internal class UserModelTest {
    @Test
    fun firstname() {
        val model = UserModel()
        model.firstname = "Adam"
        assert(model.firstname == "Adam")
    }

    @Test
    fun lastname() {
        val model = UserModel()
        model.lastname = "Ant"
        assert(model.lastname == "Ant")
    }
}