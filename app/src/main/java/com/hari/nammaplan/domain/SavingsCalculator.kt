package com.hari.nammaplan.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun calculateRemainingDays(
    currentDate: LocalDate,
    eventDate: LocalDate
): Long {
    return ChronoUnit.DAYS.between(currentDate, eventDate)
}

fun calculateBalance(
    targetAmount: Double,
    savedAmount: Double
): Double {
    return targetAmount - savedAmount
}

fun calculateDailySavings(
    balanceAmount: Double,
    remainingDays: Long
): Double {
    return balanceAmount / remainingDays
}

fun calculateSavingsProgress(
    savedAmount: Double,
    targetAmount: Double
): Float {
    return (savedAmount / targetAmount)
        .toFloat()
        .coerceIn(0f, 1f)
}