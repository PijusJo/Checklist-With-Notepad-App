package com.example.checklistwithnotepadapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.checklistwithnotepadapp.Models.CheckListEntry
import com.example.checklistwithnotepadapp.Models.TagGroup

class TagGroupViewModel(var TagName: String) : ViewModel() {

    var count: Int = 0
    var tagGroup: TagGroup = TagGroup(TagName)

    fun addEntry(entry: CheckListEntry){
        tagGroup.EntryList.add(entry)
        count++

    }

    fun removeEntry(entry: CheckListEntry){
        if(tagGroup.EntryList.contains(entry)){
            tagGroup.EntryList.remove(entry)
            count--
        }
    }
    fun EmptyEntries(){
        count = 0;
        tagGroup = TagGroup(TagName)
    }

}