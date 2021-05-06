import java.lang.NumberFormatException

fun numberToString(number: Int): String {
    if(number < 0 || number >= 1000) return "Enter number between 0 and 1000"
    val digits = arrayOf("ნული", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა")
    val decimals1 =
        arrayOf(
            "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი",
            "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი"
        )
    val decimals2 =
        arrayOf("", "ათი", "ოცი", "ოცდაათი", "ორმოცი", "ორმოცდაათი", "სამოცი", "სამოცდაათი", "ოთხმოცი", "ოთხმოცდაათი")
    val hundreds = arrayOf("ასი", "ორასი", "სამასი", "ოთხასი", "ხუთასი", "ექვსასი", "შვიდასი", "რვაასი", "ცხრაასი")

    var result = ""
    var splitNumber = number.toString().split("").drop(1).dropLast(1)

    if (splitNumber.size == 3) {
        result =
            if (number % 100 == 0) hundreds[splitNumber[0].toInt() - 1]
            else "${hundreds[splitNumber[0].toInt() - 1].dropLast(1)} "
        splitNumber = splitNumber.drop(1)
    }

    if (splitNumber.size == 2 && splitNumber[0] == "0" && splitNumber[1] != "0")
        splitNumber = splitNumber.drop(1)

    if (splitNumber.size == 2) {
        result += if (number % 10 == 0) decimals2[splitNumber[0].toInt()]
        else {
            if (decimals2[splitNumber[0].toInt()].lastIndexOf('ც') == decimals2[splitNumber[0].toInt()].length - 2)
                "${decimals2[splitNumber[0].toInt()].dropLast(1)}და${digits[splitNumber[1].toInt()]}"
            else
                "${decimals2[splitNumber[0].toInt()].dropLast(3)}${decimals1[splitNumber[1].toInt() - 1]}"
        }
    }
    if (splitNumber.size == 1)
        result += digits[splitNumber[0].toInt()]
    return "$number: $result"
}

fun main() {
    while (true) {
        val number = try {
            readLine().toString().toInt()
        } catch (ex: NumberFormatException) {
            println("Incorrect input!!!\ntry again")
            continue
        }
        println(numberToString(number))
    }
}