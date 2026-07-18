package com.hari.learningbydoing


fun main(){
    println("Enter the target amount: ")
    val targetamount=readln().toDouble()

    if(targetamount<=0){
        println("Target amount must be greater than zero")
    }else{
        println("Enter the already saved amount: ")
        val savedamount=readln().toDouble()


        if(savedamount<0){
            print("Already saved amount cannot be negative")
        }else if (savedamount>targetamount){
            println("Already saved amount cannot be greater than target amount")
        }else{
            val balanceamount=calculateBalance(targetamount,savedamount)
            println("Balance amount: $balanceamount")

            if(balanceamount==0.0){
                println("goal completed")
            }
        }
    }

}

