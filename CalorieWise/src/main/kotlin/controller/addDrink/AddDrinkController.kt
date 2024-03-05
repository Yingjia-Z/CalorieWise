package controller.addDrink

import model.UserModel
import userinterface.records.addDrink.AddDrinkEvent

class AddDrinkController(val model: UserModel) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: AddDrinkEvent, value: Any?) {
        when(event) {
            AddDrinkEvent.FirstNameEvent -> model.firstname = value as String
            AddDrinkEvent.LastNameEvent -> model.lastname = value as String
            AddDrinkEvent.UppercaseEvent -> {
                model.firstname = model.firstname.uppercase()
                model.lastname = model.lastname.uppercase()
            }
            AddDrinkEvent.LowercaseEvent -> {
                model.firstname = model.firstname.lowercase()
                model.lastname = model.lastname.lowercase()
            }
            AddDrinkEvent.ResetEvent -> {
                model.firstname = ""
                model.lastname = ""
            }
        }
    }
}