package model

enum class Screen {
    LoginPage, HomePage, BasicInfoPage, IntakePage, AddPage

    /* TODO: Food Recommendation Page missing */
}

class UserModel : IPublisher() {
    var firstname: String = ""
        set(value) {
            field = value
            notifySubscribers()
        }

    var lastname: String = ""
        set(value) {
            field = value
            notifySubscribers()
        }

    var email: String = ""
        set(value) {
            field = value
            notifySubscribers()
        }

    var password: String = ""
        set(value) {
            field = value
            notifySubscribers()
        }
    var gender: String = ""
        set(value) {
            field = value
        }

    var age: String = ""
        set(value) {
            field = value
        }

    var height: String = ""
        set(value) {
            field = value
        }

    var weight: String = ""
        set(value) {
            field = value
        }

    var goalWeight: String = ""
        set(value) {
            field = value
        }
    var calorieIntake: Int = 0
        set(value) {
            field = value
        }
    var waterIntake: Int = 0
        set(value) {
            field = value
        }
    var exerciseIntake: Int = 0
        set(value) {
            field = value
        }


    var loggedIn: Boolean = false
        set(value) {
            field = value
            notifySubscribers()
        }
}

