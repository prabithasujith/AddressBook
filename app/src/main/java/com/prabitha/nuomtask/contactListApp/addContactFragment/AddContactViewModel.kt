package com.prabitha.nuomtask.contactListApp.addContactFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prabitha.nuomtask.contactListApp.R
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContacts
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDAO
import kotlinx.coroutines.*
import java.lang.Exception

class AddContactViewModel (val dataBase: MyContactsDAO,application: Application

                           ): AndroidViewModel(application){



    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var errorMessage = MutableLiveData<String>()

    private val _navigateToContactList = MutableLiveData<Boolean?>()


    /**
     * When true immediately navigate back to the ContactsListScreen
     */
    val navigateToContactList: LiveData<Boolean?>
        get() = _navigateToContactList
    private var _showSnackbarEvent = MutableLiveData<Boolean>()


    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun onSaveContact(contact: MyContacts) {


            uiScope.launch {
                // IO is a thread pool for running operations that access the disk, such as
                // our Room database.
                validateInput(contact)

                if (errorMessage.value?.isEmpty()!!) {
                    withContext(Dispatchers.IO) {

                        insertContacts(contact)
                    }

                    _navigateToContactList.value = true
                }else
                {
                    _showSnackbarEvent.value=true
                }


        }

    }
//on done navigating
    fun doneNavigating() {
        _navigateToContactList.value = null

    }

    //on done showing the error messages
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
        errorMessage.value=""
    }


    //on validating the user inputs on click of save contact
  private fun validateInput(contact: MyContacts)
  {
try {
    if (contact.firstName.isEmpty())
        errorMessage.value=( this.getApplication<Application>().resources.getString(R.string.error_message_firstName) )
    else if (contact.lastName.isEmpty())
        errorMessage.value=(this.getApplication<Application>().resources.getString(R.string.error_message_lastName) )
    else if (contact.phoneNumber.isEmpty() || contact.phoneNumber.length != 10)
        errorMessage.value=(this.getApplication<Application>().resources.getString(R.string.error_message_phoneNumber) )
    else if (contact.email.isEmpty())
        errorMessage.value=(this.getApplication<Application>().resources.getString(R.string.error_message_email) )
    else if (contact.address.isEmpty())
        errorMessage.value=(this.getApplication<Application>().resources.getString(R.string.error_message_address) )

}
catch (error:Exception)
{
    print(error)
}

  }

    suspend fun insertContacts( contact:MyContacts) {
        withContext(Dispatchers.IO) {
            dataBase.insert(contact)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}

