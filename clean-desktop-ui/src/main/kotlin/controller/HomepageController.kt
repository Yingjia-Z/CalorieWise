package controller

import model.UserModel
import userinterface.HomepageComponent

class HomepageController(val model: UserModel) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: HomepageComponent, value: Any?) {
        when(event) {
            HomepageComponent.FirstNameEvent -> model.firstname = value as String
            HomepageComponent.LastNameEvent -> model.lastname = value as String
            HomepageComponent.UppercaseEvent -> {
                model.firstname = model.firstname.uppercase()
                model.lastname = model.lastname.uppercase()
            }
            HomepageComponent.LowercaseEvent -> {
                model.firstname = model.firstname.lowercase()
                model.lastname = model.lastname.lowercase()
            }
            HomepageComponent.ResetEvent -> {
                model.firstname = ""
                model.lastname = ""
            }
        }
    }
}