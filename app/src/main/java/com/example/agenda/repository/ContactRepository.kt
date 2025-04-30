package com.example.agenda.repository

import android.content.Context
import com.example.agenda.dao.ContactDb
import com.example.agenda.model.Contact

class ContactRepository (context: Context) {
    private val db = ContactDb.getDatabase(context).contactDao()

    fun save(contact: Contact) {
        db.create(contact);
    }

    fun update(contact: Contact) {
        db.update(contact)
    }

    fun delete(contact: Contact) {
        db.delete(contact)
    }

    fun getAll(): List<Contact> {
        return db.getAll()
    }

    fun getContactById(id: Int): Contact {
        return db.getById(id)
    }
}