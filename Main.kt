package work

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main() {
    val listOfUsers = getUsersFromFile()

//    1) Собранные все объекты User с набором имеющихся данных
    listOfUsers.forEach(::println)

//    2) Кол-во мужчин, кол-во женщин (требуется определить пол самостоятельно)
    val countOfMen = listOfUsers.count { it.sex == Sex.MALE }
    println("Количество мужчин: $countOfMen")

    val countOfWomen = listOfUsers.count { it.sex == Sex.FEMALE }
    println("Количество женщин: $countOfWomen")

//    3) Кол-во пользователей с возрастом больше 25 лет
    val ageAboveTwentyFive = listOfUsers.count { it.age > 25 }
    println("Количество людей больше 25 лет: $ageAboveTwentyFive")

//    4) Среднюю заработанную плату
    println("Средняя зарплата ${calculateAverageSalary(listOfUsers)}")

//    5) Кол-во женщин, у которых присутствует валидный номер телефона
    val countFemaleValidPhoneNumbers = listOfUsers.count { checkPhoneNumber(it.phoneNumber) && it.sex==Sex.FEMALE }
    println("Количество женщин с валидными телефонными номерами: $countFemaleValidPhoneNumbers")

//    6) Вывести пользователей, у которых имеются невалидные данные, с указанием полей не прошедших валидацию.
    listOfUsers.forEach{
        if (!checkPhoneNumber(it.phoneNumber)) println("${it.fullName} - невалидный номер")
        if (it.salary==0) println("${it.fullName} - невалидная зарплата")
    }
}

fun getUsersFromFile():List<User>{
    val file = File("src/main/kotlin/work/data.txt")
    val inputData = file.readText().trim().split("\n")
    val listOfUsers = mutableListOf<User>()
    inputData.forEach {
        val userData = it.split(";")
        val userName = userData[0]
        val date = getDate(userData[1])
        val phoneNumber = userData[2]
        val salary = try {
            userData[3].toInt()
        } catch (e:NumberFormatException){
            0
        }
        val sex = checkSex(userName)
        val age = calculateAge(date)
        val user = User(userName,date,phoneNumber,salary,sex,age)
        listOfUsers.add(user)
    }
    return listOfUsers
}

fun checkSex(fullname:String):Sex{
   return when{
        fullname.split(" ")[2].endsWith("вна") -> Sex.FEMALE
        fullname.split(" ")[2].endsWith("вич") -> Sex.MALE
        else -> Sex.NO_DATA
    }
}

fun calculateAverageSalary(list:List<User>):Double{
    return list.sumOf { it.salary }.toDouble() / list.size
}

fun calculateAge(dateOfBirh:LocalDate):Int{
   return ChronoUnit.YEARS.between(dateOfBirh, LocalDate.now()).toInt()
}

fun getDate(date:String):LocalDate{
    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return LocalDate.parse(date, dateFormat)
}

fun checkPhoneNumber(phoneNumber: String): Boolean {
    val phonePattern = Regex("""^(?:\+7|8)?\(?\d{3}\)?-?\d{2}-?\d{2}-?\d{2}-?\d{1}|\d{10}${'$'}""")
    return when {
        !phonePattern.matches(phoneNumber) -> false
        else -> true
    }
}
