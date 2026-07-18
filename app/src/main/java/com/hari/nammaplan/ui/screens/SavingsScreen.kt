package com.hari.nammaplan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.runtime.setValue

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SavingsScreen(modifier: Modifier= Modifier){
    var eventName by remember {mutableStateOf("") }
    var targetAmount by remember { mutableStateOf("") }
    var alreadySavedamount by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var  calculateButton by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("")
    }
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Namma Plan"
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Event countdown and savings calculator"
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            label = {
                Text(
                    text = "Event Name"
                )
            },
            modifier= Modifier.fillMaxWidth()
            ,
            value = eventName,
            onValueChange = {
                eventName=it
            }
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
      keyboardOptions= KeyboardOptions(
          keyboardType = KeyboardType.Decimal
      ),
            label = {
                Text(
                    text = "Target Amount"
                )
            },
            modifier= Modifier.fillMaxWidth()
            ,
            value = targetAmount,
            onValueChange = {
                targetAmount=it
            }
        )

        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            keyboardOptions= KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = {
                Text(
                    text = "Already saved Amount"
                )
            },
            modifier= Modifier.fillMaxWidth()
            ,
            value = alreadySavedamount,
            onValueChange = {
                alreadySavedamount=it
            }
        )


        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            label = {
                Text(
                    text = "Event Date",
                )
            },
            modifier= Modifier.fillMaxWidth(), placeholder = {
                Text(
                    text = "YYYY-MM-DD"
                )
            },
            value =eventDate,
            onValueChange = {
                eventDate=it
            },
            singleLine = true

        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val targetvalue=targetAmount.toDoubleOrNull()
                resultMessage = when{
                    targetvalue==null->
                        "Please enter a valid target amount"

                    }else if(0>=targetvalue){
                        Text("Target amount must be greater than zero")

                    }else{
                        Text(
                            text = "Valid target amount $targetvalue"
                        )

                    }
                }
                }
            ,
            content = {
                Text(
                    text = "Calculate plan"
                )
            }

        )



        if (resultMessage.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = resultMessage
            )
        }

    }

}

@Preview(showBackground =true)
@Composable
fun SavingsScreenPreview(){
    SavingsScreen()
}