package userinterface.records.addExercise

import androidx.compose.runtime.mutableStateOf
import model.UserModel
import userinterface.ISubscriber

class AddExerciseViewModel(val model: UserModel) : ISubscriber {
    var firstname = mutableStateOf("")
    var lastname = mutableStateOf("")

    init {
        model.subscribe(this)
    }

    override fun update() {
        firstname.value = model.firstname
        lastname.value = model.lastname
    }
}