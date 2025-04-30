package com.example.agenda.ui

import ContactAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.repository.ContactRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var contactRepository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbNewContact.setOnClickListener {
            intent = Intent(this, AddContactActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        contactRepository = ContactRepository(this)

        val contacts = contactRepository.getAll()
        contactAdapter = ContactAdapter(contacts, this)
        binding.rvContacts.adapter = contactAdapter

        binding.rvContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        contactAdapter.updateContactList(contactRepository.getAll())
    }

}