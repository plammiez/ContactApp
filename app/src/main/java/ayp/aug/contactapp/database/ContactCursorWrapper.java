package ayp.aug.contactapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import ayp.aug.contactapp.Contact;
import ayp.aug.contactapp.database.ContactDbSchema.ContactTable;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ContactCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Contact getContact() {
        String uuidString = getString(getColumnIndex(ContactTable.Cols.UUID));
        String name = getString(getColumnIndex(ContactTable.Cols.NAME));
        String tel = getString(getColumnIndex(ContactTable.Cols.TEL));
        String email = getString(getColumnIndex(ContactTable.Cols.EMAIL));

        Contact contact = new Contact(UUID.fromString(uuidString));
        contact.setName(name);
        contact.setTel(tel);
        contact.setEmail(email);

        return contact;
    }
}
