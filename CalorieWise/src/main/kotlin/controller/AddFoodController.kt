package controller

import model.UserModel
import userinterface.AddFoodEvent
import userinterface.HomepageComponent

class AddFoodController(val model: UserModel) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: AddFoodEvent, value: Any?) {
        when(event) {
            AddFoodEvent.FirstNameEvent -> model.firstname = value as String
            AddFoodEvent.LastNameEvent -> model.lastname = value as String
            AddFoodEvent.UppercaseEvent -> {
                model.firstname = model.firstname.uppercase()
                model.lastname = model.lastname.uppercase()
            }
            AddFoodEvent.LowercaseEvent -> {
                model.firstname = model.firstname.lowercase()
                model.lastname = model.lastname.lowercase()
            }
            AddFoodEvent.ResetEvent -> {
                model.firstname = ""
                model.lastname = ""
            }
        }
    }
}