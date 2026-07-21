package com.hari.nammaplan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.runtime.setValue


import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeParseException


import com.hari.nammaplan.domain.calculateDailySavings
import com.hari.nammaplan.domain.calculateRemainingDays
import com.hari.nammaplan.domain.calculateSavingsProgress
import com.hari.nammaplan.domain.calculateBalance




fun formatMoney(amount: Double): String {
    return "%.2f".format(amount)
}
@Composable
fun SavingsScreen(modifier: Modifier= Modifier){
    var progress by remember {
        mutableStateOf<Float?>(null)
    }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var eventName by remember {mutableStateOf("") }
    var targetAmount by remember { mutableStateOf("") }
    var alreadySavedamount by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("")


    }
    Column(modifier = modifier.fillMaxSize()
        .verticalScroll(
            rememberScrollState()
        ).padding(16.dp)



    ) {
        Text(
            text = "Namma Plan",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Event countdown and savings calculator",
            style = MaterialTheme.typography.bodyMedium
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
                resultMessage=""
                progress=null
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
                resultMessage=""
                progress=null
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
                resultMessage=""
                progress=null
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

            },
            singleLine = true,
            readOnly = true

        )

        Button(
            onClick = {
                showDatePicker=true
            },
            content = {
                Text(
                    text = "Select Event Date"
                )
            }
        )
        if(showDatePicker){
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker=false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selecteddatemills=datePickerState.selectedDateMillis

                            if(selecteddatemills!=null){
                                val selecteddate= Instant.ofEpochMilli(selecteddatemills).atZone(
                                    ZoneOffset.UTC).toLocalDate()


                                eventDate=selecteddate.toString()

                                resultMessage = ""
                                progress = null

                            }

                            showDatePicker=false
                        }
                    ) {
                        Text("ok")
                    }
                },
                dismissButton = {
                    TextButton(
                        {
                            showDatePicker=false

                        }
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }

















        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                progress=null
                val targetValue = targetAmount.toDoubleOrNull()
                val savedamountvalue=alreadySavedamount.toDoubleOrNull()

                val parsedeventDate=try{
                    LocalDate.parse(eventDate.trim())
                }catch (e: DateTimeParseException){null}

                val todaydate= LocalDate.now()





                resultMessage = when {
                    eventName.isBlank()->
                        "Please enter an event name"
                    eventDate.isBlank() ->
                        "Please enter an event date"

                    parsedeventDate==null->
                        "Please enter a valid date in YYYY-MM-DD format"

                    parsedeventDate.isBefore(todaydate)->
                        "Event date cannot be in the past"

                    targetValue == null ->
                        "Please enter a valid target amount"

                    targetValue <= 0 ->
                        "Target amount must be greater than zero"

                    savedamountvalue==null->
                        "Please enter valid saved amount"
                    savedamountvalue<0 ->
                        "already saved amount cannot be negative"
                    savedamountvalue>targetValue->
                        "already saved amount cannot be greater than target amount"

                    else -> {
                        val balanceAmount = calculateBalance(targetValue,savedamountvalue)
                        val remainingdays = calculateRemainingDays(todaydate, parsedeventDate)
                        progress=calculateSavingsProgress(savedamountvalue,targetValue)
                        if (balanceAmount == 0.0) {
                            "Goal already completed"
                        } else if (remainingdays == 0L) {
                            "Event is today. You need to save the full balance today: $balanceAmount"
                        } else {
                            val dayssavings = calculateDailySavings(balanceAmount,remainingdays)


                            """
        Event Name :    $eventName       
        Event Date :    $parsedeventDate
        Target Amount: ${formatMoney(targetValue)}
        Already Saved: ${formatMoney(savedamountvalue)}
        Balance amount: ${formatMoney(balanceAmount)}
        Days remaining: $remainingdays
        Daily savings needed: ${formatMoney(dayssavings)}
        """.trimIndent()


                        }
                    }

                }
            }
        ) {
            Text(
                text = "Calculate Plan"
            )
        }

        Spacer(Modifier.height(13.dp))





    Button(
        modifier= Modifier.fillMaxWidth(),
        onClick = {
            eventName = ""
            targetAmount = ""
            alreadySavedamount = ""
            eventDate = ""
            resultMessage = ""
            progress = null
            showDatePicker = false
        }

    ) {
        Text(
            text = "Reset Plan"
        )
    }

        if (resultMessage.isNotEmpty() &&progress==null) {
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = resultMessage,
                style = MaterialTheme.typography.bodyLarge
            )
        }




        progress?.let {
            currentprogress->

            Spacer(
                Modifier.height(16.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Savings Plan Result",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                    Text(
                        text =resultMessage,
                        style = MaterialTheme.typography.bodyLarge
                    )


                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )


                    LinearProgressIndicator(
                        progress = {currentprogress},
                        modifier=Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text ="Savings progress : ${(currentprogress*100).toInt()}%",
                        style = MaterialTheme.typography.titleMedium
                    )


                }
            }
        }

    }

}

@Preview(showBackground =true)
@Composable
fun SavingsScreenPreview(){
    SavingsScreen()
}