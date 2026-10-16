package com.example.checklistwithnotepadapp.Models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


data class CheckListEntry (var Description: String){

    var IsMarked by mutableStateOf(false)
    fun changeMarking(){
        IsMarked = IsMarked != true;
    }
}
