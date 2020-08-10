package com.prabitha.nuomtask.contactListApp.contactsListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContacts
import com.prabitha.nuomtask.contactListApp.databinding.ContactListItemBinding

class MyContactsListAdapter(val clickListener: MyContactListener) : RecyclerView.Adapter<MyContactsListAdapter.ContactDetailViewHolder>() {
    var data = listOf<MyContacts>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactDetailViewHolder {
        return ContactDetailViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size

    }


    override fun onBindViewHolder(
        holder: ContactDetailViewHolder,
        position: Int
    ) {
        val item = data[position]
        holder.bind(item,clickListener)
    }


    class ContactDetailViewHolder private constructor(val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(

            item: MyContacts,
            clickListener: MyContactListener
        ) {
            binding.contact = item
            binding.clickListener=clickListener
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ContactDetailViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ContactListItemBinding.inflate(layoutInflater, parent, false)
                return ContactDetailViewHolder(binding)
            }
        }
    }


}


class MyContactListener (val clickListener:   (id:Long)->Unit){
    fun onCLick(contact: MyContacts)=clickListener(contact.id)
}