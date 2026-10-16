package com.example.checklistwithnotepadapp.Models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class TagGroup(
    var tagName: String,
    var EntryList: SnapshotStateList<CheckListEntry> = mutableStateListOf()
)

