package com.example.checklistwithnotepadapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checklistwithnotepadapp.Models.CheckListEntry

@Composable
fun CL_Check(
    input: CheckListEntry,
    onDelete: () -> Unit,
    onAction: () -> Unit = {} // temporary action counter for l2 pkt
){
    Row(
        Modifier
            .background(Color.hsv(0f, 0.35f, 0.8f))
            .fillMaxWidth()
            .height(60.dp)
            .border(1.dp, Color.hsv(0f, 0.38f, 0.62f))
        ,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        //CheckmarkButton Box
        Box(
            Modifier
                .padding(10.dp)
                .size(40.dp)
                .background(Color.Gray)
                .clickable {
                    input.changeMarking()
                    onAction()
                }
        ){
            if(input.IsMarked){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Uncheck",
                    Modifier.size(40.dp)
                )
            }
        }

        //TextBox
        Box(
            Modifier
                .padding(5.dp)
                .weight(1f)
        ){
            Text(
                text = input.Description,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                textDecoration = if(input.IsMarked) TextDecoration.LineThrough else TextDecoration.None
            )
        }

        Box(
            Modifier
                .padding(10.dp)
                .size(40.dp)
                .background(Color.Gray)
                .clickable {
                    onDelete()
                    onAction()
                }
        ){
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Uncheck",
                Modifier.size(40.dp)
            )
        }

    }
}