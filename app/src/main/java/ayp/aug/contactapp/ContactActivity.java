package ayp.aug.contactapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class ContactActivity extends SingleFragmentActivity{

    protected static final String CONTACT_ID = "ContactActivity.CONTACT_ID";
    protected static final String CONTACT_POSITION = "ContactActivity.CONTACT_POS";

//    private UUID _contactId;

    @Override
    protected Fragment onCreateFragment() {
        UUID contactID = (UUID) getIntent().getSerializableExtra(CONTACT_ID);
//        int position = (int) getIntent().getExtras().get(CONTACT_POSITION);
        Fragment fragment = ContactFragment.newInstance(contactID);
        return fragment;
    }

    public static Intent newIntent(Context activity, UUID id) {
        Intent intent = new Intent(activity, ContactActivity.class);
        intent.putExtra(CONTACT_ID, id);
        return intent;
    }
}
