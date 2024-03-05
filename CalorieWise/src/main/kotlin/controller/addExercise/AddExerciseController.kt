package controller.addExercise

import model.UserModel
import userinterface.records.addExercise.AddExerciseEvent

class AddExerciseController(val model: UserModel) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: AddExerciseEvent, value: Any?) {
        when(event) {
            AddExerciseEvent.FirstNameEvent -> model.firstname = value as String
            AddExerciseEvent.LastNameEvent -> model.lastname = value as String
            AddExerciseEvent.UppercaseEvent -> {
                model.firstname = model.firstname.uppercase()
                model.lastname = model.lastname.uppercase()
            }
            AddExerciseEvent.LowercaseEvent -> {
                model.firstname = model.firstname.lowercase()
                model.lastname = model.lastname.lowercase()
            }
            AddExerciseEvent.ResetEvent -> {
                model.firstname = ""
                model.lastname = ""
            }
        }
    }
}