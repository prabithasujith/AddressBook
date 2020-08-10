package com.prabitha.nuomtask.contactListApp.contactsListFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.prabitha.nuomtask.contactListApp.R
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContactsDatabase
import com.prabitha.nuomtask.contactListApp.databinding.ContactsListFragmentBinding

class ContactsListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    val binding: ContactsListFragmentBinding= DataBindingUtil.inflate(
    inflater, R.layout.contacts_list_fragment, container, false)

        /*
               *  create the model view factory and pass the dao to the view model
               * */
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application

        val dataSource = MyContactsDatabase.getInstance(application).myContactsDatabasedao

        val viewModelFactory = MyContactsListViewModelFactory(dataSource, application)

        val myContactsListViewModel=ViewModelProvider(this,viewModelFactory).get(MyContactsListViewModel::class.java)

        // to observe the lifecycle to bind data
        binding.setLifecycleOwner(this)
        binding.contactsListViewModel=myContactsListViewModel

        //setting the adapter for recycler view in the layout
        val adapter=MyContactsListAdapter(clickListener = MyContactListener {


            myContactsListViewModel.onContactClick(it)


        })
        binding.recyclerView.adapter=adapter
        myContactsListViewModel.contacts.observe(viewLifecycleOwner, Observer {  it.let {
            adapter.data=it
        }})

        myContactsListViewModel.navigateToContactDetail.observe(viewLifecycleOwner,Observer{
            contactId-> contactId?.let {
            findNavController(this).navigate(

            ContactsListFragmentDirections.actionContactsListFragmentToContactDetailsFragment(contactId)
            )
            myContactsListViewModel.onNavigatedToContactDetail()
        }
        })



        return binding.root
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
          when(item.itemId){
            R.id.action_add_contact->  findNavController(this).navigate(ContactsListFragmentDirections.actionContactsListFragmentToAddContactFragment())
          }
    return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title ="Address Book"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }

}