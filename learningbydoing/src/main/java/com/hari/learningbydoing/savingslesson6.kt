package com.hari.learningbydoing

import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

fun remaining(currentdate: LocalDate, eventdate: LocalDate): Long{
    return  ChronoUnit.DAYS.between(currentdate,eventdate
    )
}

fun main(){
println("Enter current date (YYYY-MM-DD):")
val currentDateText = readln()

println("Enter event date (YYYY-MM-DD):")
val eventDateText = readln()

try {
    val currentDate = LocalDate.parse(currentDateText)
    print(currentDate)
    val eventDate = LocalDate.parse(eventDateText)
    println(eventDate)

    val daysRemaining =remaining(currentDate,eventDate)
    println(daysRemaining)

    if (daysRemaining < 0) {
        println("Event date cannot be in the past")
    } else if (daysRemaining == 0L) {
        println("Event is today")
    } else {
        println("Days remaining: $daysRemaining")
    }

} catch (e: DateTimeParseException) {
    println("Please enter a valid date in YYYY-MM-DD format")
}}