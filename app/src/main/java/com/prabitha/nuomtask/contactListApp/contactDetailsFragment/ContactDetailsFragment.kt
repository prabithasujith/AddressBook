package com.prabitha.nuomtask.contactListApp.contactDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prabitha.nuomtask.contactListApp.R
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDatabase
import com.prabitha.nuomtask.contactListApp.databinding.ContactDetailsFragmentBinding

class ContactDetailsFragment : Fragment() {


    lateinit var myContactDetailViewModel:MyContactDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
               *  create the model view factory and pass the dao to the view model
               * */


        val arguments = ContactDetailsFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val dataSource = MyContactsDatabase.getInstance(application).myContactsDatabasedao

        val viewModelFactory = MyContactDetailViewModelFactory(dataSource,  arguments.contactId)

         myContactDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(MyContactDetailsViewModel::class.java)
        // to observe the lifecycle to bind data

        val binding: ContactDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.contact_details_fragment, container, false
        )
        binding.lifecycleOwner = this

        myContactDetailViewModel.contact.observe(viewLifecycleOwner,Observer{
            binding.contact=it


        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


myContactDetailViewModel.contact.observe(
    this.viewLifecycleOwner, Observer {
        (activity as AppCompatActivity).supportActionBar?.title =it.firstName+ " " +it.lastName

    }
)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}