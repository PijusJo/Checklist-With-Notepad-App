package com.example.checklistwithnotepadapp

import com.example.checklistwithnotepadapp.Models.CheckListEntry
import com.example.checklistwithnotepadapp.ViewModels.TagGroupViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val TestTagName = "work"

    @Test()
    fun TestInitialization(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        assertEquals(TestTagName, TestViewModel.TagName)
        assertEquals(TestTagName, TestViewModel.tagGroup.tagName)
        assertEquals(0, TestViewModel.count)
        assertEquals(0, TestViewModel.tagGroup.EntryList.size)
    }

    @Test()
    fun TestAdd1(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry = CheckListEntry("First Task")
        TestViewModel.addEntry(CLEntry)

        assertEquals(1, TestViewModel.count)
        assertEquals(1, TestViewModel.tagGroup.EntryList.size)
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry))
    }

    @Test()
    fun TestAdd3(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry1 = CheckListEntry("First Task")
        val CLEntry2 = CheckListEntry("Second Task")
        val CLEntry3 = CheckListEntry("Third Task")

        TestViewModel.addEntry(CLEntry1)
        TestViewModel.addEntry(CLEntry2)
        TestViewModel.addEntry(CLEntry3)

        assertEquals(3, TestViewModel.count)
        assertEquals(3, TestViewModel.tagGroup.EntryList.size)
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry1))
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry2))
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry3))
    }

    @Test()
    fun TestAddAndRemove(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry1 = CheckListEntry("First Task")
        val CLEntry2 = CheckListEntry("Second Task")
        val CLEntry3 = CheckListEntry("Third Task")

        TestViewModel.addEntry(CLEntry1)
        TestViewModel.addEntry(CLEntry2)
        TestViewModel.addEntry(CLEntry3)

        TestViewModel.removeEntry(CLEntry2)
        TestViewModel.removeEntry(CLEntry3)

        assertEquals(1, TestViewModel.count)
        assertEquals(1, TestViewModel.tagGroup.EntryList.size)
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry1))
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry2))
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry3))
    }

    @Test()
    fun TestEmptyVM(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry1 = CheckListEntry("First Task")
        val CLEntry2 = CheckListEntry("Second Task")
        val CLEntry3 = CheckListEntry("Third Task")

        TestViewModel.addEntry(CLEntry1)
        TestViewModel.addEntry(CLEntry2)
        TestViewModel.addEntry(CLEntry3)
        TestViewModel.EmptyEntries()


        assertEquals(0, TestViewModel.count)
        assertEquals(0, TestViewModel.tagGroup.EntryList.size)
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry1))
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry2))
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry3))
    }
    @Test()
    fun TestRemoveNonExisting(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry1 = CheckListEntry("First Task")
        val CLEntry2 = CheckListEntry("Second Task")
        val CLEntry3 = CheckListEntry("Third Task")

        TestViewModel.addEntry(CLEntry1)

        TestViewModel.removeEntry(CLEntry2)
        TestViewModel.removeEntry(CLEntry3)

        assertEquals(1, TestViewModel.count)
        assertEquals(1, TestViewModel.tagGroup.EntryList.size)
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry1))
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry2))
        assertEquals(false, TestViewModel.tagGroup.EntryList.contains(CLEntry3))
    }

    @Test()
    fun TestAndRemoveSame(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry1 = CheckListEntry("First Task")

        TestViewModel.addEntry(CLEntry1)
        TestViewModel.addEntry(CLEntry1)
        TestViewModel.addEntry(CLEntry1)

        TestViewModel.removeEntry(CLEntry1)

        assertEquals(2, TestViewModel.count)
        assertEquals(2, TestViewModel.tagGroup.EntryList.size)
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry1))
    }

    @Test()
    fun TestMarked(){
        val TestViewModel = TagGroupViewModel(TestTagName)
        val CLEntry1 = CheckListEntry("First Task")
        val CLEntry2 = CheckListEntry("First Task")
        CLEntry2.changeMarking()
        TestViewModel.addEntry(CLEntry1)
        TestViewModel.addEntry(CLEntry2)

        assertEquals(2, TestViewModel.count)
        assertEquals(2, TestViewModel.tagGroup.EntryList.size)
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry1))
        assertEquals(true, TestViewModel.tagGroup.EntryList.contains(CLEntry2))
    }

}