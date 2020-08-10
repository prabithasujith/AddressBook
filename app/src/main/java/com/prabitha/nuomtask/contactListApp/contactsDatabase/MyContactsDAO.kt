package com.prabitha.nuomtask.contactListApp.contactsDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyContactsDAO  {

    /*
    * Inserting contact detail to table
    *
    */
    @Insert
    fun insert(night: MyContacts)

    /*
   * Update contact detail into table
   *
   */
    @Update
    fun update(night: MyContacts)

    /*
* retreiving contacts from the table
*
*/
    @Query("SELECT * from contacts_table WHERE id = :key")
    fun get(key: Long): LiveData<MyContacts>?


    @Query("SELECT * from contacts_table")
    fun getAllContacts():LiveData<List<MyContacts>>
}