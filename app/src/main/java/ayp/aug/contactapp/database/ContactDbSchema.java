package ayp.aug.contactapp.database;

/**
 * Created by Waraporn on 8/9/2016.
 */
public class ContactDbSchema {

    public static final class ContactTable{

        public static final String NAME = "contacts";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String TEL = "tel";
            public static final String EMAIL = "email";
        }

    }
}
