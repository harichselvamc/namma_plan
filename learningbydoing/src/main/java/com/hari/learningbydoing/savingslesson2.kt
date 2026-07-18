package com.hari.learningbydoing

fun calculateBalance(
    targetamount: Double,
    alreadysaved: Double
): Double{
    return targetamount-alreadysaved
}
fun main(){
    println("Enter the target amount: ")
    val targetamount=readln().toDouble()
    println("Enter the already saved amount: ")
    val savedamount=readln().toDouble()

    val balanceamount=calculateBalance(
        targetamount,savedamount
    )
    println("Target Amount          : $targetamount")
    println("Already saved amount   : $savedamount")
    println("Balance amount         : $balanceamount")

}

