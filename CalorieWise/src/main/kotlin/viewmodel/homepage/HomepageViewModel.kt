package viewmodel.homepage

import androidx.compose.runtime.mutableStateOf
import model.UserModel
import userinterface.ISubscriber

class HomepageViewModel(val model: UserModel) : ISubscriber {
    var calorieEaten = mutableStateOf(model.calorieTaken)
    var calorieIntake = mutableStateOf(model.recommendedCaloryIntake)
    var calorieBurned = mutableStateOf(model.calorieBurned)

    init {
        model.subscribe(this)
    }

    override fun update() {
        calorieEaten.value = model.calorieTaken
        calorieIntake.value = model.recommendedCaloryIntake
        calorieBurned.value = model.calorieBurned
    }
}