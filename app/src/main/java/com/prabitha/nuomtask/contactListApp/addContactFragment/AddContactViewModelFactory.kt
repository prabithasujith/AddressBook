package com.prabitha.nuomtask.contactListApp.addContactFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDAO

class AddContactViewModelFactory(private val dataSource: MyContactsDAO,
                                 private val application: Application

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            return AddContactViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}