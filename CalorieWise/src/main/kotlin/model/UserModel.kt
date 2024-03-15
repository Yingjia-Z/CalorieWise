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
    var recommendedCaloryIntake: Int = 0
    var recommendedWaterIntake: Int = 0
    var recommendedExercistIntake: Int = 0
    var recommendedFatIntake: Int = 0
    var recommendedProteinIntake: Int = 0
    var recommendedSugarIntake: Int = 0



    var loggedIn: Boolean = false
        set(value) {
            field = value
            notifySubscribers()
        }

    var calorieTaken: Int = 0
        set(value) {
            field = value
            notifySubscribers()
        }

    var calorieBurned: Int = 0
        set(value) {
            field = value
            notifySubscribers()
        }

    var waterTaken: Int = 0
        set(value) {
            field = value
            notifySubscribers()
        }

    var fatTaken: Int = 0
        set(value) {
            field = value
            notifySubscribers()
        }

    var proteinTaken: Int = 0
        set(value) {
            field = value
            notifySubscribers()
        }

    var sugerTaken: Int = 0
        set(value) {
            field = value
            notifySubscribers()
        }
}

