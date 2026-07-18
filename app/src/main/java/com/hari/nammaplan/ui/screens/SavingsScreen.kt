package com.hari.nammaplan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SavingsScreen(modifier: Modifier= Modifier){

    Column(modifier = Modifier) {
        Text(
            text = "Namma Plan"
        )
        Text(
            text = "Event countdown and savings calculator"
        )
    }

}

@Preview(showBackground =true)
@Composable
fun SavingsScreenPreview(){
    SavingsScreen(modifier = Modifier.padding())
}