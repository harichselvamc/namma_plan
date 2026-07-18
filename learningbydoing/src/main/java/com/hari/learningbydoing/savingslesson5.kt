package com.hari.learningbydoing

fun perdaysavings(balanceamount: Double,remainingday: Int): Double{
    return balanceamount/remainingday
}











fun main(){
    println("Enter the target amount:")
    val targetAmount = readln().toDoubleOrNull()

    if (targetAmount == null) {
        println("Please enter a valid number")
    } else if (targetAmount <= 0) {
        println("Target amount must be greater than zero")
    } else {
        println("Valid target amount: $targetAmount")
        println("Enter the already saved amount: ")
        val savedamount=readln().toDoubleOrNull()
        if(savedamount==null){
            println("Please enter a valid saved amount")
        }
        else if(savedamount<0){
            print("Already saved amount cannot be negative")
        }else if (savedamount>targetAmount){
            println("Already saved amount cannot be greater than target amount")
        } else {
        val balanceAmount = calculateBalance(
            targetAmount,
            savedamount
        )

        println("Balance amount: $balanceAmount")

        println("Enter days remaining:")
        val daysRemaining = readln().toIntOrNull()

        if (daysRemaining == null) {
            println("Please enter valid days")

        } else if (daysRemaining < 0) {
            println("Days remaining cannot be negative")

        } else if (daysRemaining == 0) {
            println("Event is today")
            println("Amount needed today: $balanceAmount")

        } else {
            val perDayAmount = perdaysavings(
                balanceAmount,
                daysRemaining
            )

            println("Per day saving amount: $perDayAmount")
        }

        if (balanceAmount == 0.0) {
            println("Goal completed")
        }
    }





    }

}

