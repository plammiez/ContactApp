package ayp.aug.contactapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import ayp.aug.contactapp.database.ContactDbSchema.ContactTable;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "ContactBase.db";
    private static final String TAG = "ContactBaseHelper";

    public ContactBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    /**
     * use this to create database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Create Database");
        db.execSQL("create table " + ContactTable.NAME
                + "("
                + "_id integer primary key autoincrement, "
                + ContactTable.Cols.UUID + ","
                + ContactTable.Cols.NAME + ","
                + ContactTable.Cols.TEL + ","
                + ContactTable.Cols.EMAIL + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
