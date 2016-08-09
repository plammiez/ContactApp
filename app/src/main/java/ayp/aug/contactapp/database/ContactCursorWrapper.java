package ayp.aug.contactapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

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
}
