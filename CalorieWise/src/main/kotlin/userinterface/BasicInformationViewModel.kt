package userinterface

import androidx.compose.runtime.mutableStateOf
import model.UserModel
import kotlin.math.roundToInt

class BasicInformationViewModel(val model: UserModel) : ISubscriber {
    private var gender: String = ""
    private var age: String = ""
    private var height: String = ""
    private var weight: String = ""
    private var goalWeight: String = ""
    var calorie:Int = 0
    var waterIntake:Int = 0
    var exercise:Int = 0

    init {
        model.subscribe(this)
    }
    //get the latest data from the model.
    override fun update() {
        gender = model.gender
        age = model.age
        height = model.height
        goalWeight = model.goalWeight
        calorie = model.calorieIntake
        waterIntake = model.waterIntake
        exercise = model.exerciseIntake
    }

    //update the model with information from user input.
    fun updateBasicInformation(gender: String, age: String, height: String,
                               weight: String, goalWeight: String, calorieIntake: Int,
                               waterIntake: Int, exerciseIntake: Int) {
        model.gender = gender
        model.age = age
        model.height = height
        model.weight = weight
        model.goalWeight = goalWeight
        model.calorieIntake = calorieIntake
        model.waterIntake = waterIntake
        model.exerciseIntake = exerciseIntake
        model.notifySubscribers()
    }

    fun setGender(value: String) {
        gender = value
    }

    fun setAge(value: String) {
        age = value
    }

    fun setHeight(value: String) {
        height = value
    }

    fun setWeight(value: String) {
        weight = value
    }
    fun setGoalWeight(value: String) {
        goalWeight = value
    }

    fun setCalorieIntake(value: Int) {
        calorie = value
    }

    fun setwaterIntake(value: Int) {
        waterIntake = value
    }
    fun setExerciseIntake(value: Int) {
        exercise = value
    }

    fun calculateCalroieIntake(): Int {
        //for women: BMR = 655 + (9.6 × body weight in kg) + (1.8 × body height in cm) - (4.7 × age in years);
        // for men: BMR = 66 + (13.7 × weight in kg) + (5 × height in cm) - (6.8 × age in years).
        var calories = 0.0;
        val weight = weight.toDouble()
        val height = height.toDouble()
        val age = age.toDouble()

        if(gender.equals("M")){
            calories = 66 + (13.7 * weight) + (5 * height) - (6.8 * age)
        }else if(gender.equals("F")){
            calories = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)
        }
        return calories.roundToInt()
    }
    fun calculateWaterIntake(): Int {
        //multuply by 67%, gives answer in ounces
        var water = 0.0;
        val weight = weight.toDouble()

        return (weight*0.67).roundToInt()
    }
    fun calculateExercise():Int {
        /*TODO: no formula found???*/
        return 0
    }

}



