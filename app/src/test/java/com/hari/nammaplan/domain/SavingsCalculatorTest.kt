package com.hari.nammaplan.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class SavingsCalculatorTest {

    @Test
    fun calculateBalanceReturnsCorrectBalance() {
        // Arrange
        val targetAmount = 3000.0
        val savedAmount = 1000.0
        val expectedBalance = 2000.0

        // Act
        val actualBalance = calculateBalance(
            targetAmount = targetAmount,
            savedAmount = savedAmount
        )

        // Assert
        assertEquals(
            expectedBalance,
            actualBalance,
            0.001
        )
    }
    fun calculateBalanceReturnsZeroBalance() {
        // Arrange
        val targetAmount = 3000.0
        val savedAmount = 3000.0
        val expectedBalance = 0.0

        // Act
        val actualBalance = calculateBalance(
            targetAmount = targetAmount,
            savedAmount = savedAmount
        )

        // Assert
        assertEquals(
            expectedBalance,
            actualBalance,
            0.001
        )
    }

    @Test
    fun calculateDailySavingsReturnsCorrectAmount() {
        // Arrange
        val balanceAmount = 1000.0
        val remainingDays = 2L
        val expectedDailySavings = 500.0

        // Act
        val actualDailySavings = calculateDailySavings(
            balanceAmount = balanceAmount,
            remainingDays = remainingDays
        )

        // Assert
        assertEquals(
            expectedDailySavings,
            actualDailySavings,
            0.001
        )
    }

    @Test
    fun calculateSavingsProgressReturnsCorrectProgress() {
        // Arrange
        val savedAmount = 500.0
        val targetAmount = 1000.0
        val expectedProgress = 0.5f

        // Act
        val actualProgress = calculateSavingsProgress(
            savedAmount = savedAmount,
            targetAmount = targetAmount
        )

        // Assert
        assertEquals(
            expectedProgress,
            actualProgress,
            0.001f
        )
    }


    @Test
    fun FutureEventTest(){
        //Arrange
        var currentdate= LocalDate.of(2026,5,8)
        //LocalDate.parse("2026-07-20")
        var eventdate= LocalDate.of(2027,5,8)
        var expectedate=365L


        //ACT
        var balancedays=calculateRemainingDays(
            currentdate,eventdate
        )


        //Assert

        assertEquals(
            expectedate,balancedays
        )
    }


    @Test
    fun SameDayEventTest(){

        //arrange
        val currentdate= LocalDate.parse("2026-05-08")
        val Eventdate= LocalDate.parse("2026-05-08")
        var expectedday=0L
        //act

        val actualday=calculateRemainingDays(
            currentdate,Eventdate
        )

        //assert
        assertEquals(expectedday,actualday)

    }

}