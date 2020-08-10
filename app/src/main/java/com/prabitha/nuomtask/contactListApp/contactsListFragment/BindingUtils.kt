package com.prabitha.nuomtask.contactListApp.contactsListFragment

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.prabitha.nuomtask.contactListApp.contactsDatabase.MyContacts

@BindingAdapter(value = ["contactName"])
fun TextView.setContactName(item: MyContacts?){
    item?.let {
        text= """${item.firstName} ${item.lastName}"""
    }
}