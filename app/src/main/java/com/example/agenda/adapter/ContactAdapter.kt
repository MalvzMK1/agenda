import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.model.Contact
import com.example.agenda.R
import com.example.agenda.ui.AddContactActivity

class ContactAdapter(private var contactList: List<Contact>, private var context: Context): RecyclerView.Adapter<ContactAdapter.HolderContact>() {

    fun updateContactList(newContactList: List<Contact>) {
        this.contactList = newContactList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderContact {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return HolderContact(view, context)
    }

    override fun onBindViewHolder(holder: HolderContact, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class HolderContact(view: View, private var context: Context): RecyclerView.ViewHolder(view) {
        private val textNameCt: TextView = view.findViewById(R.id.text_view_name)
        private val textEmailCt: TextView = view.findViewById(R.id.text_view_email)
        private val textPhoneCt: TextView = view.findViewById(R.id.text_view_phone)
        private val textNameInitialCt: TextView = view.findViewById(R.id.text_view_name_initial)
        private val cardViewContact: CardView = view.findViewById(R.id.card_view_contact)

        fun bind(contact: Contact) {

            textNameCt.text = contact.name
            textEmailCt.text = contact.email
            textPhoneCt.text = contact.phone
            textNameInitialCt.text = contact.name.substring(0, 1)

            cardViewContact.setOnClickListener {
                val openNewContactActivity = Intent(context, AddContactActivity::class.java)

                openNewContactActivity.putExtra("contactId", contact.id)

                context.startActivity(openNewContactActivity)
            }

        }

    }

}