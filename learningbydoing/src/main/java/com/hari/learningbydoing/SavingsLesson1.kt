package com.hari.learningbydoing

fun main(){
    println("Enter the target amount: ")
    val targetamount=readln().toDouble()
    println("Enter the already saved amount: ")
    val savedamount=readln().toDouble()

    val balanceamount=targetamount-savedamount


    println("Target Amount          : $targetamount")
    println("Already saved amount   : $savedamount")
    println("Balance amount         : $balanceamount")

}