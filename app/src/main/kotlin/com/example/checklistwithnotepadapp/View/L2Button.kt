package com.example.checklistwithnotepadapp.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun L2Button(
OnStart : () -> Unit,
ButtonText: String
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Button(
            onClick = {OnStart()},
        ){
            Text(ButtonText)
        }
    }
}