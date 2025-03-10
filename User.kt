package work

import java.time.LocalDate

data class User(
    val fullName: String,
    val dateOfBirth: LocalDate,
    val phoneNumber: String,
    val salary: Int,
    val sex: Sex,
    val age:Int
)

enum class Sex(val title:String){
    MALE("Мужской"),FEMALE("Женский"),NO_DATA("Неизвестно");

    override fun toString(): String {
        return this.title
    }

}