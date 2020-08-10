package com.prabitha.nuomtask.contactListApp.contactsListFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDAO

class MyContactsListViewModelFactory(
    private val dataSource: MyContactsDAO,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyContactsListViewModel::class.java)) {
            return MyContactsListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}