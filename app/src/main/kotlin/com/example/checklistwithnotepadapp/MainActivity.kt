package com.example.checklistwithnotepadapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checklistwithnotepadapp.Models.CheckListEntry
import com.example.checklistwithnotepadapp.View.CLAddEntryColumn
import com.example.checklistwithnotepadapp.View.CL_Check
import com.example.checklistwithnotepadapp.View.L2Button
import com.example.checklistwithnotepadapp.ViewModels.TagGroupViewModel
import com.example.checklistwithnotepadapp.ui.theme.ChecklistWithNotepadAppTheme
import kotlin.time.TimeSource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChecklistWithNotepadAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        var hasStarted = remember {
                            mutableStateOf(false)
                        }

                        var tagGroupVM = remember {
                            TagGroupViewModel("the tag")
                        }
                        var NotFirstTime = remember {
                            mutableStateOf(false)
                        }

                        var actionCount = remember {mutableStateOf(0)}
                        var TodoRunTimeInSeconds = remember {mutableStateOf(0L)}
                        var starttime = TimeSource.Monotonic.markNow()

                        var Filter by remember {mutableStateOf("All")}


                        if(!hasStarted.value){
                            Column(modifier = Modifier.fillMaxSize()){
                                L2Button(
                                    {
                                        tagGroupVM.EmptyEntries()
                                        hasStarted.value = true
                                        actionCount.value = 0;
                                        starttime = TimeSource.Monotonic.markNow()
                                        NotFirstTime.value = true
                                        Filter = "All"
                                    },
                                    "Start"
                                )
                                if(NotFirstTime.value){
                                    Text("Actions: ${actionCount.value}")
                                    Text("RunTime: ${TodoRunTimeInSeconds.value}s")
                                    Text("TodoList:\n")
                                    Box(modifier = Modifier.fillMaxSize()){
                                        LazyColumn() {
                                            items(tagGroupVM.tagGroup.EntryList){entry ->
                                            Text(
                                                "\nEntry Description: ${entry.Description}\n" +
                                                        "Is marked: ${entry.IsMarked}")

                                            }
                                        }
                                    }

                                }
                            }
                        }
                        else{
                            Column(modifier = Modifier.fillMaxSize()){
                                L2Button(
                                    {
                                        val TodoRunTime = starttime.elapsedNow()
                                        TodoRunTimeInSeconds.value = TodoRunTime.inWholeSeconds
                                        hasStarted.value = false

                                    },
                                    "End"
                                )

                                CLAddEntryColumn(
                                    OnAddEntry = { newItem ->
                                        tagGroupVM.addEntry(CheckListEntry(newItem))
                                        actionCount.value++
                                    })
                                FilterButton({ NewFilter ->
                                    Filter = NewFilter
                                })
                                Box(modifier = Modifier.weight(1f)){
                                    CheckList(Filter, tagGroupVM, onAction = {actionCount.value++})
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun CheckList(
    //modifier: Modifier = Modifier,
    filter: String,
    tagGroupVM: TagGroupViewModel,
    onAction: () ->Unit = {}
) {
    Box(
    //modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        )
        {
            items(items = tagGroupVM.tagGroup.EntryList) { Entry ->
                if(filter == "All")
                    CL_Check(
                        Entry,
                        onDelete = {tagGroupVM.removeEntry(Entry)},
                        onAction
                    )
                else if(filter == "Active" && !Entry.IsMarked)
                    CL_Check(
                        Entry,
                        onDelete = {tagGroupVM.removeEntry(Entry)},
                        onAction
                    )
                else if (filter == "Done" && Entry.IsMarked)
                    CL_Check(
                        Entry,
                        onDelete = {tagGroupVM.removeEntry(Entry)},
                        onAction
                    )

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChecklistWithNotepadAppTheme {
        var tagGroupVM = remember {
            TagGroupViewModel("the tag")
        }
        tagGroupVM.addEntry(CheckListEntry("aaa"))

        CheckList("All", tagGroupVM)
    }
}

@Composable
fun FilterButton(OnChangeFilter:(String)->Unit){
    var currentFilter = remember {mutableStateOf("All")}
    Row(
        Modifier
            .height(60.dp)
            .border(1.dp, Color.hsv(0f, 0.38f, 0.62f))
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        //TextBox
        Box(
            Modifier
                .padding(5.dp)
                .clickable(){
                    if(currentFilter.value == "All")
                        currentFilter.value  = "Active"
                    else if( currentFilter.value  == "Active" )
                        currentFilter.value  = "Done"
                    else
                        currentFilter.value  = "All"
                    OnChangeFilter(currentFilter.value )
                }
        ){
            Text(
                text = "Filter: ${currentFilter.value} ",
                fontSize = 20.sp,
                textAlign = TextAlign.Left
            )
        }
    }
}