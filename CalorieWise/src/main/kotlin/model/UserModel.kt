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

    var loggedIn: Boolean = false
        set(value) {
            field = value
            notifySubscribers()
        }
}