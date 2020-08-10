package com.prabitha.nuomtask.contactListApp.addContactFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.prabitha.nuomtask.contactListApp.R
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContacts
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDatabase
import com.prabitha.nuomtask.contactListApp.databinding.AddContactFragmentBinding


/*
* Add contact screen: User can add new contact
*
* */

class AddContactFragment : Fragment() {
lateinit var contact: MyContacts


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding: AddContactFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.add_contact_fragment, container, false)
        /*
        *  create the model view factory and pass the dao to the view model
        * */
        val application = requireNotNull(this.activity).application

        val dataSource = MyContactsDatabase.getInstance(application).myContactsDatabasedao

        val viewModelFactory = AddContactViewModelFactory(dataSource, application)

        val myAddContactViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddContactViewModel::class.java)


        binding.LastName

        binding.addContact.setOnClickListener {

        contact= MyContacts( firstName = binding.editTextFirstName.text.toString(),
            phoneNumber =  binding.editTextPhone.text.toString() ,
            lastName = binding.LastName.text.toString(),
            email = binding.editTextTextEmailAddress.text.toString(),
            address = binding.editTextTextPostalAddress.text.toString()

            )

            myAddContactViewModel.onSaveContact(contact)

            }


        myAddContactViewModel.navigateToContactList.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                NavHostFragment.findNavController(this).navigate(R.id.action_addContactFragment_to_contactsListFragment)

                myAddContactViewModel.doneNavigating()
            }
        })

        myAddContactViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {

                Snackbar.make(binding.root,myAddContactViewModel.errorMessage.value.toString(),Snackbar.LENGTH_LONG).show()
                myAddContactViewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title ="Add New Contact"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}