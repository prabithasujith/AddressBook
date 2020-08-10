package com.prabitha.nuomtask.contactListApp.contactsDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class MyContacts(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="first_name")
    val firstName: String = "",
    @ColumnInfo(name="last_name")
    val lastName: String = "",
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String ="",
    @ColumnInfo(name="email_id")
    val email: String="",
    @ColumnInfo(name="address")
    val address: String=""

)