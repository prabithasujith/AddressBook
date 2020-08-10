package com.prabitha.nuomtask.contactListApp.contactsDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1,entities = [MyContacts::class],exportSchema = false)
abstract class MyContactsDatabase : RoomDatabase() {

    abstract  val myContactsDatabasedao:MyContactsDAO


    /*
       * companion object is used to access the methods for creating or getting the db without instantiate it
       * */

    companion object {

        /*
        volatile: this helps us to know the value of instance is visible to all immediately
        *
        * */
        @Volatile
        private var INSTANCE: MyContactsDatabase? = null

        fun getInstance(context: Context): MyContactsDatabase {
            synchronized(this) {
                var instance = INSTANCE


                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyContactsDatabase::class.java,
                        "my_contacts_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}