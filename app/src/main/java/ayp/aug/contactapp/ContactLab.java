package ayp.aug.contactapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    private Context context;

    private ContactLab(Context context){
        this.context = context;
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();
        return contacts;
    }
}
