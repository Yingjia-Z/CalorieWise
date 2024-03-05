package model

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

    var age: Int = -1
        set(value) {
            field = value
        }

    var height: Int = -1
        set(value) {
            field = value
        }

    var weight: Int = -1
        set(value) {
            field = value
        }

    var goalWeight: Int = -1
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

