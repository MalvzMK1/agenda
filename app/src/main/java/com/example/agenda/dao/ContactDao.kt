package com.example.agenda.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.agenda.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): List<Contact>

    @Query("SELECT * FROM contacts WHERE id = (:contactId)")
    fun getById(contactId: Int): Contact

    @Insert
    fun create(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Update
    fun update(contact: Contact)
}