package com.prabitha.nuomtask.contactListApp.contactDetailsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContacts
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDAO


class MyContactDetailsViewModel(
    database: MyContactsDAO,
    val id:Long //contactId
): ViewModel() {
var contact:  LiveData<MyContacts> = database.get(id)!!
}