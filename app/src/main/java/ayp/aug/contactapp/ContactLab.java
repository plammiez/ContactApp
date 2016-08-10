package ayp.aug.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ayp.aug.contactapp.database.ContactBaseHelper;
import ayp.aug.contactapp.database.ContactCursorWrapper;
import ayp.aug.contactapp.database.ContactDbSchema;
import ayp.aug.contactapp.database.ContactDbSchema.ContactTable;

/**
 * Created by Waraporn on 8/9/2016.
 */
public class ContactLab {

    private static final String TAG = "ContactLab";
    private static ContactLab instance;

    public static ContactLab getInstance(Context context){

        if(instance == null){
            instance = new ContactLab(context);
        }
        return instance;
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactTable.Cols.UUID, contact.getId().toString());
        contentValues.put(ContactTable.Cols.NAME, contact.getName());
        contentValues.put(ContactTable.Cols.TEL, contact.getTel());
        contentValues.put(ContactTable.Cols.EMAIL, contact.getEmail());
        return contentValues;
    }


    private Context context;
    private SQLiteDatabase database;

    private ContactLab(Context context){
        this.context = context.getApplicationContext();
        ContactBaseHelper contactBaseHelper = new ContactBaseHelper(context);
        database = contactBaseHelper.getWritableDatabase();

    }


    public Contact getContactById (UUID uuid) {
        ContactCursorWrapper cursor = queryContact(ContactTable.Cols.UUID + " = ?", new String[]{uuid.toString()} );

        try {
            if(cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getContact();
        } finally {
            cursor.close();
        }
    }

    private ContactCursorWrapper queryContact(String whereCause, String[] whereArgs) {
        Cursor cursor = database.query(ContactTable.NAME,
                null,
                whereCause,
                whereArgs,
                null,
                null,
                null);
        return new ContactCursorWrapper(cursor);
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();

        ContactCursorWrapper cursor = queryContact(null,null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                contacts.add(cursor.getContact());

                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return contacts;

    }

    public void addContact(Contact contact){
        Log.d(TAG, "ADD Contact" + contact.toString());
        ContentValues contentValues = getContentValues(contact);

        database.insert(ContactTable.NAME, null, contentValues);
    }

    public void deleteContact(UUID uuid) {
        database.delete(ContactTable.NAME, ContactTable.Cols.UUID
                + " = ? ", new String[] {uuid.toString() });
    }

    public void updateContact(Contact contact){
        String uuidStr = contact.getId().toString();
        ContentValues contentValues = getContentValues(contact);

        database.update(ContactTable.NAME, contentValues, ContactTable.Cols.UUID
                + " = ?", new String[] { uuidStr}); // uuidStr will manage n put in ? position (sql injection)
    }

    public File getPhotoFile(Contact contact) {
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (externalFilesDir == null) {
            return null;
        }
        return  new File(externalFilesDir, contact.getPhotoFileName());
    }

}
