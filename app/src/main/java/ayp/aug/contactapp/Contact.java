package ayp.aug.contactapp;

import java.util.UUID;

/**
 * Created by Waraporn on 8/9/2016.
 */
public class Contact {

    private static final String TAG = "Contact";

    private UUID id;
    private String name;
    private String tel;
    private String email;

    public Contact() {
        this(UUID.randomUUID());
    }

    public Contact(UUID uuid) {
        this.id = uuid;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoFileName() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
