package com.example.checklistwithnotepadapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CLAddEntryColumn(OnAddEntry: (String) -> Unit) {
    var entryText = remember {
        mutableStateOf("")
    }
    Row(
        Modifier
            .background(Color.hsv(0f, 0.35f, 0.8f))
            .height(60.dp)
            .border(1.dp, Color.hsv(0f, 0.38f, 0.62f))
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            value = entryText.value,
            label = {Text ("New Entry")},
            onValueChange ={
                if(it.contains('\n')){
                    val SplitText = it.split('\n', limit = 2)
                    if(SplitText[0].isNotEmpty())
                        OnAddEntry(SplitText[0])
                    entryText.value = SplitText[1]
                }
                else{
                    entryText.value = it
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}