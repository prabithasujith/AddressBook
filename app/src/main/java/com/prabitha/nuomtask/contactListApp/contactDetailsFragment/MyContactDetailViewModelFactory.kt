package com.prabitha.nuomtask.contactListApp.contactDetailsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDAO

class MyContactDetailViewModelFactory(private val dataSource: MyContactsDAO,
                                      private val id: Long
): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyContactDetailsViewModel::class.java)) {
            return MyContactDetailsViewModel(dataSource,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}