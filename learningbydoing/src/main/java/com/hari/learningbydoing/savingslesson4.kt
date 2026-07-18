package com.hari.learningbydoing


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
                }else{
                    val balanceamount=calculateBalance(targetAmount,savedamount)
                    println("Balance amount: $balanceamount")

                    if(balanceamount==0.0){
                        println("goal completed")
                    }
            }





        }

}

