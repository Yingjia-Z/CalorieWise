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

    var age: Int = -1

    var height: Int = -1

    var weight: Int = -1

    var goalWeight: Int = -1
    var calorieIntake: Int = 0
    var waterIntake: Int = 0
    var exerciseIntake: Int = 0


    var loggedIn: Boolean = false
        set(value) {
            field = value
            notifySubscribers()
        }

    var recordItem: String = ""
    var recordType: String = ""
    var recordAmount: Int = -1
}

