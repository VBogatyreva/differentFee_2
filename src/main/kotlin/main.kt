fun main() {
    val fee = calculateFee("Mastercard", 75_000, 10_000, 30_000, 150_000, 600_000)

    if (fee == 0) {
        println("Превышен лимит. Операция отклонена.")

    } else {
        println("Перевод выполнен!")
        println("Сумма комиссии: " + fee)
    }
}

fun calculateFee(
    userCard: String,
    summaTransaction: Int,
    totalTransactionDay: Int,
    totalTransactionMonth: Int,
    limitDay: Int,
    limitMonth: Int
): Any {

    println("Перевод в сумме: " + summaTransaction)
    println("Сумма переводов за день: " + totalTransactionDay)
    println("Сумма переводов за месяц: " + totalTransactionMonth)


    var summaFee = when (userCard) {
        "Mastercard" -> {
            if ((totalTransactionMonth + summaTransaction) <= calculateLimitPerCard(userCard)) 0.00
            else if (calculateLimitPerCard(userCard) < (totalTransactionMonth + summaTransaction) && totalTransactionMonth < calculateLimitPerCard(
                    userCard
                )
            ) ((((totalTransactionMonth + summaTransaction - calculateLimitPerCard(userCard)) * calculateDiscount(
                discount = userCard
            )) + calculateAddSum(level = userCard)))
            else ((summaTransaction * calculateDiscount(discount = userCard)) + calculateAddSum(level = userCard))
        }

        "Visa" -> ((((summaTransaction - calculateLimitPerCard(limitPerCard = userCard)) * calculateDiscount(discount = userCard) + calculateAddSum(
            level = userCard
        ))))

        "Мир" -> (((summaTransaction - calculateLimitPerCard(limitPerCard = userCard)) * calculateDiscount(discount = userCard) + calculateAddSum(
            level = userCard
        )))

        else -> 0.00

    }

    var checkLimit = when (userCard) {
        else -> if ((totalTransactionDay + summaTransaction) <= limitDay && (totalTransactionMonth + summaTransaction) <= limitMonth) summaFee else 0
    }

    var checkMinFee = when (userCard) {
        "Visa" -> if (summaFee > 35.0) checkLimit else 35.0
        else -> checkLimit
    }
    return checkMinFee
}


fun calculateLimitPerCard(limitPerCard: String): Int {
    var userLimitPerCard = when (limitPerCard) {
        "Mastercard" -> 75_000
        else -> 0
    }
    return userLimitPerCard
}

fun calculateAddSum(level: String): Int {
    var userAddSum = when (level) {
        "Mastercard" -> 20
        else -> 0
    }
    return userAddSum
}

fun calculateDiscount(discount: String): Double {
    var userDiscount = when (discount) {
        "Mastercard" -> 0.006
        "Visa" -> 0.0075
        else -> 0.00
    }
    return userDiscount
}
 