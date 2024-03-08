package viewmodel

import model.UserModel
import userinterface.ViewEvent
import kotlin.test.Test

/*
class UserControllerTest {
    @Test
    fun setup() {
        val model = UserModel()
        val controller = UserController(model)

        assert(controller.model == model)
    }

    @Test
    fun properties() {
        val model = UserModel()
        val controller = UserController(model)

        controller.invoke(ViewEvent.FirstNameEvent, "Adam")
        assert(model.firstname == "Adam")

        controller.invoke(ViewEvent.LastNameEvent, "Ant")
        assert(model.lastname == "Ant")
    }

    @Test
    fun uppercase() {
        val model = UserModel()
        val controller = UserController(model)

        controller.invoke(ViewEvent.FirstNameEvent, "Adam")
        controller.invoke(ViewEvent.UppercaseEvent, null)
        assert(model.firstname == "ADAM")

        controller.invoke(ViewEvent.LastNameEvent, "Ant")
        controller.invoke(ViewEvent.UppercaseEvent, null)
        assert(model.lastname == "ANT")
    }

    @Test
    fun lowercase() {
        val model = UserModel()
        val controller = UserController(model)

        controller.invoke(ViewEvent.FirstNameEvent, "Adam")
        controller.invoke(ViewEvent.LowercaseEvent, null)
        assert(model.firstname == "adam")

        controller.invoke(ViewEvent.LastNameEvent, "Ant")
        controller.invoke(ViewEvent.LowercaseEvent, null)
        assert(model.lastname == "ant")
    }

    @Test
    fun reset() {
        val model = UserModel()
        val controller = UserController(model)
        model.firstname = "Adam"
        model.lastname = "Ant"

        controller.invoke(ViewEvent.ResetEvent, null)
        assert(model.firstname == "")
        assert(model.lastname == "")
    }
}
*/