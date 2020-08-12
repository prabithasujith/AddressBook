package com.prabitha.nuomtask.contactListApp.contactsListFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDAO
import kotlinx.coroutines.Job

class MyContactsListViewModel (
    database: MyContactsDAO,
    application: Application): AndroidViewModel(application) {

    val contacts = database.getAllContacts()

    private val _navigateToContactDetail=MutableLiveData<Long>()
    val navigateToContactDetail
    get()=_navigateToContactDetail

// variable to decide whether to show the place holder view or not

    val noItemsVisible =Transformations.map(contacts){
        it.size==0
    }

// variable to decide whether to show the recycler view or not
    val recyclerViewVisible = Transformations.map(contacts){
        it.size>0
    }

    fun onContactClick(id: Long) {
     _navigateToContactDetail.value=id
    }

    fun onNavigatedToContactDetail(){
        _navigateToContactDetail.value=null
    }

}