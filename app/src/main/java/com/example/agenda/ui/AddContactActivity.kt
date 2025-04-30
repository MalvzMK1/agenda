package com.example.agenda.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.databinding.ActivityAddContactBinding
import com.example.agenda.model.Contact
import com.example.agenda.repository.ContactRepository

class AddContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding
    private lateinit var contactRepository: ContactRepository
    private var id = 0
    private var contact = Contact()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactRepository = ContactRepository(this)

        binding.buttonSave.setOnClickListener {
            save()
        }

        binding.buttonDelete.setOnClickListener {
            delete()
        }

        id = intent.getIntExtra("contactId", 0)

        if (id > 0) {
            binding.buttonDelete.visibility = View.VISIBLE
            binding.buttonSave.text = "Atualizar"
            loadContact()
        }
    }

    private fun loadContact() {
        contact = contactRepository.getContactById(id)

        binding.editTextName.setText(contact.name)
        binding.editTextEmail.setText(contact.email)
        binding.editTextPhone.setText(contact.phone)
    }

    private fun save() {
        contact.name = binding.editTextName.text.toString()
        contact.phone = binding.editTextPhone.text.toString()
        contact.email = binding.editTextEmail.text.toString()

        if (id > 0) {
            contactRepository.update(contact)
        } else {
            contactRepository.save(contact)
        }

        finish()
    }

    private fun delete() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exclusão")
        builder.setMessage("Confirma a exclusão do(a) ${contact.name}")
        builder.setPositiveButton("Sim"){ _, _ ->
            contactRepository.delete(contact)
            finish()
        }
        builder.setNegativeButton("Não"){ _, _ ->

        }

        builder.show()
    }

}